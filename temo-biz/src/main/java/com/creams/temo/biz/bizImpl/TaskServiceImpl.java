package com.creams.temo.biz.bizImpl;

import com.alibaba.fastjson.JSON;
import com.creams.temo.TaskScheduler;
import com.creams.temo.biz.DingdingService;
import com.creams.temo.biz.TaskService;
import com.creams.temo.biz.TestCaseSetService;
import com.creams.temo.convert.TaskDto2TaskBo;
import com.creams.temo.convert.TimingTaskDto2TimingTaskBo;
import com.creams.temo.entity.*;

import com.creams.temo.mapper.SetResultMapper;
import com.creams.temo.mapper.TaskMapper;

import com.creams.temo.mapper.TaskResultMapper;
import com.creams.temo.model.*;
import com.creams.temo.tools.DateUtil;
import com.creams.temo.tools.DingdingUtils;
import com.creams.temo.tools.StringUtil;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.shiro.SecurityUtils;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * 任务service
 */

@Service
public class TaskServiceImpl implements TaskService {


    @Autowired
    public TaskMapper taskMapper;


    @Autowired
    public TaskScheduler taskScheduler;

    @Autowired
    public TestCaseSetService testCaseSetService;

    @Autowired
    public TaskResultMapper taskResultMapper;

    @Autowired
    public SetResultMapper setResultMapper;

    @Autowired
    public DingdingService dingdingService;

    final TaskDto2TaskBo taskDto2TaskBo = TaskDto2TaskBo.getInstance();
    final TimingTaskDto2TimingTaskBo timingTaskDto2TimingTaskBo = TimingTaskDto2TimingTaskBo.getInstance();

    /**
     * 新增任务
     *
     * @param taskRequest
     */
    public void addTask(TaskBo taskRequest) {
        taskRequest.setTaskId(StringUtil.uuid());
        UserBo user = (UserBo) SecurityUtils.getSubject().getPrincipal();
        taskRequest.setCreator(user.getUserName());
        taskMapper.addTask(taskDto2TaskBo.reverse().convert(taskRequest));
    }

    /**
     * 编辑任务
     *
     * @param taskRequest
     */
    public void updateTask(TaskBo taskRequest) {
        taskMapper.updateTaskById(taskDto2TaskBo.reverse().convert(taskRequest));
    }

    /**
     * 删除任务
     *
     * @param id
     */
    @Transactional
    public void deleteTask(String id) {
        taskMapper.deleteTask(id);
    }

    /**
     * 根据任务名和状态筛选任务
     */
    public PageInfo<TimingTaskBo> queryTasks(Integer page, String taskName, String isParallel) {
        PageHelper.startPage(page, 10);
        List<TimingTaskDto> taskResponses = taskMapper.queryTasks(taskName, isParallel);
        List<TimingTaskBo> timingTaskBos = Lists.newArrayList(timingTaskDto2TimingTaskBo.convertAll(taskResponses));
        for (TimingTaskBo taskResponse : timingTaskBos) {
            List<TestSet> testSets = JSON.parseArray(taskResponse.getTestSets().replaceAll("\\\\", ""), TestSet.class);
            taskResponse.setTestSetList(testSets);
        }
        return new PageInfo<>(timingTaskBos);
    }

    /**
     * 根据定时任务名和状态筛选定时任务
     */
    public PageInfo<TimingTaskBo> queryTimingTasks(Integer page, String taskName, String isParallel) {
        PageHelper.startPage(page, 10);
        List<TimingTaskDto> timingTaskResponses = taskMapper.queryTimingTasks(taskName, isParallel);
        List<TimingTaskBo> timingTaskBos = Lists.newArrayList(timingTaskDto2TimingTaskBo.convertAll(timingTaskResponses));
        for (TimingTaskBo timingTaskResponse : timingTaskBos) {
            List<TestSet> testSets = JSON.parseArray(timingTaskResponse.getTestSets().replaceAll("\\\\", ""), TestSet.class);
            timingTaskResponse.setTestSetList(testSets);
        }
        return new PageInfo<>(timingTaskBos);
    }

    /**
     * 查询任务详情
     *
     * @param taskId
     */
    public TimingTaskBo queryTaskDetail(String taskId) {
        TimingTaskDto taskResponse = taskMapper.queryTaskDetail(taskId);
        if (taskResponse!=null){
            List<TestSet> testSets = JSON.parseArray(taskResponse.getTestSets().replaceAll("\\\\", ""), TestSet.class);
            taskResponse.setTestSetList(testSets);
        }
        return timingTaskDto2TimingTaskBo.convert(taskResponse);
    }

    /**
     * 查询定时任务详情
     *
     * @param taskId
     */
    public TimingTaskBo queryTimingTaskDetail(String taskId) {
        TimingTaskDto timingTaskResponse = taskMapper.queryTimingTaskDetail(taskId);
        List<TestSet> testSets = JSON.parseArray(timingTaskResponse.getTestSets().replaceAll("\\\\", ""), TestSet.class);
        for (TestSet testSet : testSets) {
            testSet.setSetName(testCaseSetService.queryTestCaseSetInfo(testSet.getSetId()).getSetName());
        }
        timingTaskResponse.setTestSetList(testSets);
        return timingTaskDto2TimingTaskBo.convert(timingTaskResponse);
    }

    @Transactional
    public void addTimingTask(TimingTaskBo timingTaskRequest) {
        timingTaskRequest.setTaskId(StringUtil.uuid());
        taskMapper.addTimingTask(timingTaskDto2TimingTaskBo.reverse().convert(timingTaskRequest));
    }

    /**
     * 编辑定时任务
     *
     * @param timingTaskRequest
     */
    public void updateTimingTask(TimingTaskBo timingTaskRequest) {
        taskMapper.updateTimingTask(timingTaskDto2TimingTaskBo.reverse().convert(timingTaskRequest));
    }

    /**
     * 发起串行任务
     *
     * @param taskId
     */
    @Async
    public void startSynchronizeTask(String taskId) {
        // 获取任务相关联的需要执行的用例集
        TimingTaskBo taskResponse = queryTaskDetail(taskId);
        DingdingEntity dingdingEntity = dingdingService.queryDingdingBydescId(taskResponse.getDingId());
        UserBo user;
        try{
            user = (UserBo) SecurityUtils.getSubject().getPrincipal();
        }catch (Exception e){
            user = null;
        }
        String taskResultId = StringUtil.uuid();
        TaskResult taskResult = TaskResult.builder()
                .taskResultNum("task-" + System.currentTimeMillis())
                .taskName(taskResponse.getTaskName())
                .status(1)
                .person(user!=null?user.getUserName():"系统定时执行")
                .taskId(taskId)
                .taskResultId(taskResultId)
                .startTime(DateUtil.getCurrentTimestamp()).build();
        taskResultMapper.addTaskResult(taskResult);
        // 转换json
        List<TestSet> testSets = JSON.parseArray(taskResponse.getTestSets().replaceAll("\\\\", ""), TestSet.class);
        // 立即执行
        Integer total = testSets.size();
        Integer successNum = 0;
        // 发送钉钉通知
        long beforeTime = System.currentTimeMillis();
        String dingMsg;
        TextEntity textEntity;
        if (taskResponse.getIsDing()==1){
            dingMsg = String.format("\n任务：%s\n执行方式：串行\n包含用例集：%d 个\n现在开始执行......",
                    taskResponse.getTaskName(),total);
            textEntity = TextEntity.builder()
                    .atMobiles(Collections.emptyList())
                    .content(dingMsg)
                    .isAtAll(false)
                    .build();
            DingdingUtils.sendToDingding(textEntity.getJSONObjectString(dingdingEntity.getKeysWord()),dingdingEntity.getWebhook());
        }

        for (TestSet testSet : testSets) {
            try {
                // 同步调用用例集
                Boolean result = testCaseSetService.executeSetBySynchronizeTask(taskResultId, testSet);
                if (result) {
                    successNum++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 更新执行记录状态
        taskResult.setEndTime(DateUtil.getCurrentTimestamp());
        taskResult.setStatus(2);
        taskResult.setSuccessNum(successNum);
        taskResult.setTotalNum(total);
        // 计算成功率
        DecimalFormat df = new DecimalFormat("0.00");
        String successRate;
        if (total == 0) {
            successRate = "0.00";
        } else {
            successRate = df.format(((float) successNum / total) * 100);
        }
        long afterTime = System.currentTimeMillis();
        if (taskResponse.getIsDing()==1){
            dingMsg = String.format("\n任务：%s 执行结束！本次共执行用例集 %d 个，成功 %d 个，失败 %d 个，成功率为 %s %%,共耗时 %d s",
                    taskResponse.getTaskName(),total,successNum,total-successNum,successRate,(afterTime-beforeTime)/1000);
            textEntity = TextEntity.builder()
                    .atMobiles(Collections.emptyList())
                    .content(dingMsg)
                    .isAtAll(false)
                    .build();
            DingdingUtils.sendToDingding(textEntity.getJSONObjectString(dingdingEntity.getKeysWord()),dingdingEntity.getWebhook());
        }

        taskResult.setSuccessRate(successRate);
        taskResultMapper.updateTaskResult(taskResult);

    }

    /**
     * 发起并发任务
     *
     * @param taskId
     */
    @Async
    public void startAsnchronizeTask(String taskId) {
        // 获取任务相关联的需要执行的用例集
        TimingTaskBo taskResponse = queryTaskDetail(taskId);
        DingdingEntity dingdingEntity = dingdingService.queryDingdingBydescId(taskResponse.getDingId());
        // 添加执行记录
        UserBo user;
        try{
            user = (UserBo) SecurityUtils.getSubject().getPrincipal();
        }catch (Exception e){
            user = null;
        }
        String taskResultId = StringUtil.uuid();
        TaskResult taskResult = TaskResult.builder()
                .taskResultNum("task-" + System.currentTimeMillis())
                .taskName(taskResponse.getTaskName())
                .status(1)
                .person(user!=null?user.getUserName():"系统定时执行")
                .taskId(taskId)
                .taskResultId(taskResultId)
                .startTime(DateUtil.getCurrentTimestamp()).build();
        taskResultMapper.addTaskResult(taskResult);
        // 转换json
        List<TestSet> testSets = JSON.parseArray(taskResponse.getTestSets().replaceAll("\\\\", ""), TestSet.class);
        // 所有的执行结果future
        List<Future<Boolean>> results = new ArrayList<>();
        // 立即执行
        Integer total = testSets.size();
        Integer successNum = 0;
        // 发送钉钉通知
        long beforeTime = System.currentTimeMillis();
        String dingMsg;
        TextEntity textEntity;
        if (taskResponse.getIsDing()==1){
            dingMsg = String.format("\n任务：%s\n执行方式：并行\n包含用例集：%d 个\n现在开始执行......",
                    taskResponse.getTaskName(),total);
            textEntity = TextEntity.builder()
                    .atMobiles(Collections.emptyList())
                    .content(dingMsg)
                    .isAtAll(false)
                    .build();
            DingdingUtils.sendToDingding(textEntity.getJSONObjectString(dingdingEntity.getKeysWord()),dingdingEntity.getWebhook());
        }
        for (TestSet testSet : testSets)
            try {
                // 异步调用用例集
                Future<Boolean> result = testCaseSetService.executeSetByAsynchronizeTask(taskResultId, testSet);
                results.add(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        while (true) {
            boolean isAllDone = true;
            for (Future<Boolean> result : results) {
                if (!result.isDone()) {
                    // 如果没执行完，标记isAllDone为false
                    isAllDone = false;
                }
            }
            if (isAllDone) {
                break;
            }
        }
        for (Future<Boolean> result : results) {
            try {
                if (result.get()) {
                    successNum++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        // 更新执行记录状态
        taskResult.setEndTime(DateUtil.getCurrentTimestamp());
        taskResult.setStatus(2);
        taskResult.setSuccessNum(successNum);
        taskResult.setTotalNum(total);
        // 计算成功率
        DecimalFormat df = new DecimalFormat("0.00");
        String successRate;
        if (total == 0) {
            successRate = "0.00";
        } else {
            successRate = df.format(((float) successNum / total) * 100);
        }
        long afterTime = System.currentTimeMillis();
        if (taskResponse.getIsDing()==1){
            dingMsg = String.format("\n任务：%s 执行结束！本次共执行用例集 %d 个，成功 %d 个，失败 %d 个，成功率为 %s %%,共耗时 %d s",
                    taskResponse.getTaskName(),total,successNum,total-successNum,successRate,(afterTime-beforeTime)/1000);
            textEntity = TextEntity.builder()
                    .atMobiles(Collections.emptyList())
                    .content(dingMsg)
                    .isAtAll(false)
                    .build();
            DingdingUtils.sendToDingding(textEntity.getJSONObjectString(dingdingEntity.getKeysWord()),dingdingEntity.getWebhook());
        }
        taskResult.setSuccessRate(successRate);
        taskResultMapper.updateTaskResult(taskResult);


    }

    /**
     * 开启定时任务
     *
     * @param taskId
     */
    @Transactional
    public void startTimingTask(String taskId) {
        // 获取任务相关联的需要执行的用例集
        TimingTaskBo timingTaskResponse = queryTimingTaskDetail(taskId);
        // 更新状态为开启
        taskMapper.updateTimingTaskStatus(taskId, 1);
        // 定时执行

        //定义一个Trigger
        Trigger trigger = newTrigger().withIdentity(timingTaskResponse.getTaskId())
                .startNow()//一旦加入scheduler，立即生效
                .withSchedule(cronSchedule(timingTaskResponse.getCron()))
                .build();
        //定义一个JobDetail
        JobDetail job = newJob(TaskScheduler.class)
                .withIdentity(timingTaskResponse.getTaskId())
                .usingJobData("taskId", taskId)
                .build();
        // 把job加入到任务调度器
        taskScheduler.addJob(job, trigger);
    }

    /**
     * 关闭定时任务
     *
     * @param taskId
     */
    @Transactional
    public void closeTimingTask(String taskId) {
        // 更新状态为开启
        taskMapper.updateTimingTaskStatus(taskId, 0);
        taskScheduler.deleteJob(taskId);
    }

    /**
     * 查询执行任务结果
     *
     * @param page
     * @param taskName
     * @param status
     * @return
     */
    public PageInfo<TaskResult> queryTaskResults(Integer page, String taskName, Integer status) {
        PageHelper.startPage(page, 10);
        List<TaskResult> taskResults = taskResultMapper.queryTaskResults(taskName, status);
        return new PageInfo<>(taskResults);
    }

    /**
     * 查询任务执行接结果详情
     *
     * @param taskResultId
     * @return
     */
    public List<SetResult> queryTaskResultDetail(String taskResultId) {
        return setResultMapper.querySetResultsByTaskResultId(taskResultId);
    }
}

