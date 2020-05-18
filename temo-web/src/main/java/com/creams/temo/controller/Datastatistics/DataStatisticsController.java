package com.creams.temo.controller.Datastatistics;

import com.creams.temo.biz.*;
import com.creams.temo.convert.*;
import com.creams.temo.entity.result.JsonResult;
import com.creams.temo.model.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("DataStatisticsController Api")
@RestController
@RequestMapping("/dataStatistics")
public class DataStatisticsController {

    @Autowired
    ExecuteTodayService executeTodayService;

    @Autowired
    ExecuteTimeInfoService executeTimeInfoService;

    @Autowired
    TasksInfoService tasksInfoService;

    @Autowired
    TestCaseSetInfoService testCaseSetInfoService;

    @Autowired
    SevenDaysExecuteInfoService sevenDaysExecuteInfoService;

    final ExecuteSevenDaysAo2ExecuteSevenDaysBo executeSevenDaysAo2ExecuteSevenDaysBo= ExecuteSevenDaysAo2ExecuteSevenDaysBo.getInstance();
    final ExecuteTimeInfoAo2ExecuteTimeInfoBo executeTimeInfoAo2ExecuteTimeInfoBo =ExecuteTimeInfoAo2ExecuteTimeInfoBo.getInstance();
    final ExecuteTodayAo2ExecuteTodayBo executeTodayAo2ExecuteTodayBo=ExecuteTodayAo2ExecuteTodayBo.getInstance();
    final TaskInfoAo2TaskInfoBo taskInfoAo2TaskInfoBo =TaskInfoAo2TaskInfoBo.getInstance();
    final TesSetInfoAo2TesSetInfoBo tesSetInfoAo2TesSetInfoBo =TesSetInfoAo2TesSetInfoBo.getInstance();
    @ApiOperation(value = "查询任务库信息")
    @GetMapping("/taskInfo")
    public JsonResult queryTasksInfo() {
        try {
            TaskInfoBo taskInfoResponse = tasksInfoService.queryTasksInfo();
            return new JsonResult("获取成功", 200, taskInfoAo2TaskInfoBo.reverse().convert(taskInfoResponse), true);
        }catch (Exception e){
            return new JsonResult("获取失败", 500, null, false);
        }
    }

    @ApiOperation(value = "查询任务执行信息")
    @GetMapping("/taskExecuteInfo")
    public JsonResult queryTasksExecuteInfo() {
        try {
            TaskInfoBo taskInfoResponse = tasksInfoService.queryTasksExecuteInfo();
            return new JsonResult("获取成功", 200, taskInfoAo2TaskInfoBo.reverse().convert(taskInfoResponse), true);
        }catch (Exception e){
            return new JsonResult("获取失败", 500, null, false);
        }
    }


    @ApiOperation(value = "查询用例库信息")
    @GetMapping("/setInfo")
    public JsonResult queryTestCaseSetInfo() {
        try {
            TesSetInfoBo tesSetInfoResponse = testCaseSetInfoService.queryTestSetInfo();
            return new JsonResult("获取成功", 200, tesSetInfoAo2TesSetInfoBo.reverse().convert(tesSetInfoResponse), true);
        }catch (Exception e){
            return new JsonResult("获取失败", 500, null, false);
        }

    }

    @ApiOperation(value = "查询今日执行情况")
    @GetMapping("/todayInfo")
    public JsonResult queryTodayExecuteTaskInfo() {
        try {
            ExecuteTodayBo executeTodayResponse = executeTodayService.queryTodayExecuteTaskInfo();
            if (executeTodayResponse!=null){
                return new JsonResult("获取成功", 200, executeTodayAo2ExecuteTodayBo.reverse().convert(executeTodayResponse), true);
            }else{
                ExecuteTodayAo exeuteTodayAo= ExecuteTodayAo.builder()
                        .executeCaseTodayNum(0)
                        .falseNum("0")
                        .successNum("0")
                        .successRate("0.00").build();
                return new JsonResult("获取成功", 200, exeuteTodayAo, true);
            }
        }catch (Exception e){
            return new JsonResult("获取失败", 500, null, false);
        }

    }

    @ApiOperation(value = "查询今日用例执行情况")
    @GetMapping("/todayCaseInfo")
    public JsonResult queryTodayExecuteTestCaseInfo() {
        try {
            ExecuteTodayBo executeTodayResponse = executeTodayService.queryTodayExecuteTestCaseInfo();
            if (executeTodayResponse!=null){
                return new JsonResult("获取成功", 200, executeTodayAo2ExecuteTodayBo.reverse().convert(executeTodayResponse), true);
            }else {
                ExecuteTodayAo exeuteTodayAo= ExecuteTodayAo.builder()
                        .executeCaseTodayNum(0)
                        .falseNum("0")
                        .successNum("0")
                        .successRate("0.00").build();
                return new JsonResult("获取成功", 200, exeuteTodayAo, true);
            }

        }catch (Exception e){
            return new JsonResult("获取失败", 500, null, false);
        }

    }


    @ApiOperation(value = "查询运行信息")
    @GetMapping("/executeInfoNow")
    public JsonResult queryExecuteTaskInfoNow() {
        try {
            ExecuteTimeInfoBo executeInfoResponse = executeTimeInfoService.queryExecuteTimeInfo();
            return new JsonResult("获取成功", 200,executeTimeInfoAo2ExecuteTimeInfoBo.reverse().convert(executeInfoResponse), true);
        }catch (Exception e){
            return new JsonResult("获取失败", 500, null, false);
        }
    }

    @ApiOperation(value = "查询七日用例执行情况")
    @GetMapping("/SevenDaysInfo")
    public JsonResult querySevenDaysExecuteInfo() {
        try {
            ExecuteSevenDaysBo executeSevenDaysResponse = sevenDaysExecuteInfoService.querySevenDaysExecuteInfo();
            return new JsonResult("获取成功", 200, executeSevenDaysAo2ExecuteSevenDaysBo.reverse().convert(executeSevenDaysResponse), true);
        }catch (Exception e){
            return new JsonResult("获取失败", 500, null, false);
        }
    }
}
