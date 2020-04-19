package com.creams.temo.biz;

import com.creams.temo.entity.SetResult;
import com.creams.temo.entity.TaskResult;
import com.creams.temo.model.TaskBo;
import com.creams.temo.model.TimingTaskBo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface TaskService {
    void addTimingTask(TimingTaskBo task);

    PageInfo<TimingTaskBo> queryTimingTasks(Integer page, String taskName, String isParallel);

    TimingTaskBo queryTimingTaskDetail(String taskId);

    void updateTimingTask(TimingTaskBo timingTaskRequest);

    void deleteTask(String taskId);

    void startTimingTask(String taskId);

    void closeTimingTask(String taskId);

    PageInfo<TaskResult> queryTaskResults(Integer page, String taskName, Integer status);

    List<SetResult> queryTaskResultDetail(String taskResultId);

    void addTask(TaskBo convert);

    PageInfo<TimingTaskBo> queryTasks(Integer page, String taskName, String isParallel);

    TimingTaskBo queryTaskDetail(String taskId);

    void updateTask(TaskBo convert);

    void startSynchronizeTask(String taskId);

    void startAsnchronizeTask(String taskId);
}
