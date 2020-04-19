package com.creams.temo.mapper;


import com.creams.temo.model.TaskDto;
import com.creams.temo.model.TimingTaskDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TaskMapper {


    List<TimingTaskDto> queryTasks(String taskName, String isParallel);

    @Select("select * from task where task_id = #{taskId}")
    TimingTaskDto queryTaskDetail(String taskId);

    void addTask(TaskDto taskRequest);

    void updateTaskById(TaskDto taskRequest);

    @Delete("delete from task where task_id = #{task_id}")
    void deleteTask(@Param("task_id") String taskId);

    void addTimingTask(TimingTaskDto timingTaskRequest);

    void updateTimingTask(TimingTaskDto timingTaskRequest);

    List<TimingTaskDto> queryTimingTasks(String taskName, String isParallel);

    @Update("update task set is_open = #{isOpen} where task_id = #{taskId}")
    void updateTimingTaskStatus(String taskId, Integer isOpen);

    @Select("select * from task where task_id = #{taskId}")
    TimingTaskDto queryTimingTaskDetail(String taskId);
}
