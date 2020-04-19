package com.creams.temo.mapper;


import com.creams.temo.model.TaskDto;
import com.creams.temo.model.TimingTaskDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TaskMapper {


    List<TimingTaskDto> queryTasks(String taskName, String isParallel);

    TimingTaskDto queryTaskDetail(@Param("task_id") String taskId);

    void addTask(TaskDto taskRequest);

    void updateTaskById(TaskDto taskRequest);

    void deleteTask(@Param("task_id") String taskId);

    void addTimingTask(TimingTaskDto timingTaskRequest);

    void updateTimingTask(TimingTaskDto timingTaskRequest);

    List<TimingTaskDto> queryTimingTasks(String taskName, String isParallel);

    void updateTimingTaskStatus(@Param("task_id") String taskId, @Param("is_open") Integer isOpen);

    TimingTaskDto queryTimingTaskDetail(@Param("task_id")String taskId);
}
