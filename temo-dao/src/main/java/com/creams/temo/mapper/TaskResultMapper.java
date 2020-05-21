package com.creams.temo.mapper;

import com.creams.temo.entity.TaskResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TaskResultMapper {


    void addTaskResult(TaskResult taskResult);

    void updateTaskResult(TaskResult taskResult);

    List<TaskResult>  queryTaskResults(@Param("taskName") String taskName, @Param(value = "status") Integer status);

    TaskResult queryTaskResult(String taskResultId);

}
