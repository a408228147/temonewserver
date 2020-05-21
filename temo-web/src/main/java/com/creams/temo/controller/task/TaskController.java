package com.creams.temo.controller.task;

import com.creams.temo.annotation.CheckPermissions;
import com.creams.temo.biz.TaskService;
import com.creams.temo.convert.TaskAo2TaskBo;
import com.creams.temo.convert.TimingTaskAo2TimingTaskBo;
import com.creams.temo.entity.result.JsonResult;
import com.creams.temo.model.TaskAo;
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
import java.util.concurrent.ExecutionException;

/**
 * 任务
 *
 */
@RestController
@Api("TaskController APi")
@RequestMapping("/task")
public class TaskController {

    @Autowired
    public TaskService taskService;
   final TaskAo2TaskBo taskAo2TaskBo=TaskAo2TaskBo.getInstance();
   final TimingTaskAo2TimingTaskBo timingTaskAo2TimingTaskBo =TimingTaskAo2TimingTaskBo.getInstance();

    @ApiOperation(value = "新增任务")
    @PostMapping("/")
    @CheckPermissions()
    public JsonResult addTask(@RequestBody TaskAo task) {
        try {
            taskService.addTask(taskAo2TaskBo.convert(task));
            return new JsonResult("操作成功", 200, null, true);
        } catch (Exception e) {
            return new JsonResult("操作失败", 500, e, false);
        }

    }

    @ApiOperation(value = "根据任务名和执行方式查询任务")
    @GetMapping("/{page}")
    @CheckPermissions()
    public JsonResult queryTasks(@PathVariable(value = "page") Integer page, @RequestParam(value = "taskName", required = false) String taskName, @RequestParam(value = "isParallel", required = false) String isParallel) {
        PageInfo<TimingTaskAo> pageInfo =new PageInfo<>(Lists.newArrayList(timingTaskAo2TimingTaskBo.reverse().convertAll(taskService.queryTasks(page,taskName, isParallel).getList())));
        HashMap<String,Object> map = new HashMap<>();
        map.put("list", pageInfo.getList());
        map.put("total", pageInfo.getTotal());
        return new JsonResult("操作成功", 200, map, true);
    }



    @ApiOperation(value = "查询任务详情")
    @GetMapping("/{taskId}/info")
    @CheckPermissions()
    public JsonResult queryTaskDetail(@PathVariable("taskId") String taskId) {
        TimingTaskBo taskResponse = taskService.queryTaskDetail(taskId);
        return new JsonResult("操作成功", 200,timingTaskAo2TimingTaskBo.reverse().convert(taskResponse), true);
    }


    @ApiOperation(value = "编辑任务")
    @PutMapping("/{taskId}")
    @CheckPermissions()
    public JsonResult updateTask(@PathVariable("taskId") String taskId, @RequestBody TaskAo taskRequest) {
        try {
            taskService.updateTask(taskAo2TaskBo.convert(taskRequest));
            return new JsonResult("操作成功", 200, null, true);
        } catch (Exception e) {
            return new JsonResult("操作失败", 500, e, false);
        }
    }


    @ApiOperation(value = "删除任务")
    @DeleteMapping("/{taskId}")
    @CheckPermissions()
    public JsonResult updateTask(@PathVariable("taskId") String taskId) {
        try {
            taskService.deleteTask(taskId);
            return new JsonResult("操作成功", 200, null, true);
        } catch (Exception e) {
            return new JsonResult("操作失败", 500, e, false);
        }

    }


    @ApiOperation(value = "发起普通任务")
    @PostMapping("/startTask/{taskId}")
    @CheckPermissions()
    public JsonResult startTask(@PathVariable("taskId") String taskId) throws ExecutionException, InterruptedException {
        TimingTaskBo taskResponse = taskService.queryTaskDetail(taskId);
        String isParallel = taskResponse.getIsParallel();
        // 判断是并发执行还是同步执行
        if ("0".equals(isParallel)) {
            taskService.startSynchronizeTask(taskId);
            return new JsonResult("已成功发起普通任务", 200, null, true);
        } else {
            taskService.startAsnchronizeTask(taskId);
            return new JsonResult("已成功发起并发任务", 200, null, true);
        }
    }

    @ApiOperation(value = "批量发起普通任务")
    @PostMapping("/startTasks")
    @CheckPermissions()
    public JsonResult startTasks(@RequestBody List<String> taskIds) throws ExecutionException, InterruptedException {
        for (String taskId : taskIds){
            TimingTaskBo taskResponse = taskService.queryTaskDetail(taskId);
            String isParallel = taskResponse.getIsParallel();
            // 判断是并发执行还是同步执行
            if ("0".equals(isParallel)) {
                taskService.startSynchronizeTask(taskId);
            } else {
                taskService.startAsnchronizeTask(taskId);
            }
        }
        return new JsonResult("已批量发起任务", 200, null, true);
    }
}
