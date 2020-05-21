package com.creams.temo.controller.task;


import com.creams.temo.annotation.CheckPermissions;
import com.creams.temo.biz.TaskService;
import com.creams.temo.convert.TimingTaskAo2TimingTaskBo;
import com.creams.temo.entity.result.JsonResult;
import com.creams.temo.model.TimingTaskAo;
import com.creams.temo.model.TimingTaskBo;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * 任务
 */
@RestController
@Api("TimingTaskController APi")
@RequestMapping("/timingTask")
public class TimingTaskController {

    @Autowired
    public TaskService taskService;
    final TimingTaskAo2TimingTaskBo timingTaskAo2TimingTaskBo=TimingTaskAo2TimingTaskBo.getInstance();

    @ApiOperation(value = "新增定时任务")
    @PostMapping("/")
    @CheckPermissions()
    public JsonResult addTask(@RequestBody TimingTaskAo task) {
        try {
            taskService.addTimingTask(timingTaskAo2TimingTaskBo.convert(task));
            return new JsonResult("操作成功", 200, null, true);
        } catch (Exception e) {
            return new JsonResult("操作失败", 500, e, false);
        }

    }

    @ApiOperation(value = "根据定时任务名和执行方式查询定时任务")
    @GetMapping("/{page}")
    @CheckPermissions()
    public JsonResult queryTasks(@PathVariable(value = "page") Integer page, @RequestParam(value = "taskName", required = false) String taskName, @RequestParam(value = "isParallel", required = false) String isParallel) {
        PageInfo<TimingTaskAo> pageInfo = new PageInfo<>(Lists.newArrayList(timingTaskAo2TimingTaskBo.reverse().convertAll(taskService.queryTimingTasks(page, taskName, isParallel).getList())));
        HashMap<String, Object> map = new HashMap<>();
        map.put("list", pageInfo.getList());
        map.put("total", pageInfo.getTotal());
        return new JsonResult("操作成功", 200, map, true);
    }


    @ApiOperation(value = "查询定时任务详情")
    @GetMapping("/{taskId}/info")
    @CheckPermissions()
    public JsonResult queryTaskDetail(@PathVariable("taskId") String taskId) {
        TimingTaskBo timingTaskResponse = taskService.queryTimingTaskDetail(taskId);
        return new JsonResult("操作成功", 200, timingTaskAo2TimingTaskBo.reverse().convert(timingTaskResponse), true);
    }


    @ApiOperation(value = "编辑定时任务")
    @PutMapping("/{taskId}")
    @CheckPermissions()
    public JsonResult updateTask(@PathVariable("taskId") String taskId, @RequestBody TimingTaskAo timingTaskRequest) {
        try {
            taskService.updateTimingTask(timingTaskAo2TimingTaskBo.convert(timingTaskRequest));
            return new JsonResult("操作成功", 200, null, true);
        } catch (Exception e) {
            return new JsonResult("操作失败", 500, e, false);
        }
    }


    @ApiOperation(value = "删除定时任务")
    @DeleteMapping("/{taskId}")
    @CheckPermissions()
    public JsonResult deleteTask(@PathVariable("taskId") String taskId) {
        try {
            taskService.deleteTask(taskId);
            return new JsonResult("操作成功", 200, null, true);
        } catch (Exception e) {
            return new JsonResult("操作失败", 500, e, false);
        }
    }


    @ApiOperation(value = "开启定时任务")
    @PostMapping("/start/{taskId}")
    @CheckPermissions()
    public JsonResult startTimingTask(@PathVariable("taskId") String taskId) {
        try {
            taskService.startTimingTask(taskId);
            return new JsonResult("已成功开启定时任务", 200, null, true);
        } catch (Exception e) {
            return new JsonResult("发生未知错误", 500, e, false);
        }

    }

    @ApiOperation(value = "关闭定时任务")
    @PostMapping("/close/{taskId}")
    @CheckPermissions()
    public JsonResult closeTimingTask(@PathVariable("taskId") String taskId) {
        try {
            taskService.closeTimingTask(taskId);
            return new JsonResult("已关闭开启定时任务", 200, null, true);
        } catch (Exception e) {
            return new JsonResult("发生未知错误", 500, e, false);
        }
    }


    @ApiOperation(value = "批量开启定时任务")
    @PostMapping("/startTasks")
    @CheckPermissions()
    public JsonResult startTimingTasks(@RequestBody List<String> taskIds) {
        try {
            for (String taskId : taskIds) {
                if (taskService.queryTimingTaskDetail(taskId).getIsOpen() == 0) {
                    taskService.startTimingTask(taskId);
                }
            }
            return new JsonResult("已批量开启定时任务", 200, null, true);
        } catch (Exception e) {
            return new JsonResult("发生未知错误", 500, e, false);
        }

    }

    @ApiOperation(value = "批量关闭定时任务")
    @PostMapping("/closeTasks")
    @CheckPermissions()
    public JsonResult closeTimingTask(@RequestBody List<String> taskIds) {
        try {
            for (String taskId : taskIds) {
                if (taskService.queryTimingTaskDetail(taskId).getIsOpen() == 1) {
                    taskService.closeTimingTask(taskId);
                }
            }
            return new JsonResult("已批量关闭定时任务", 200, null, true);
        } catch (Exception e) {
            return new JsonResult("发生未知错误", 500, e, false);
        }
    }

}
