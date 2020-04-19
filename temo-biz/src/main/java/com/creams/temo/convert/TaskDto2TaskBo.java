package com.creams.temo.convert;

import com.creams.temo.model.TaskBo;
import com.creams.temo.model.TaskDto;
import com.google.common.base.Converter;

public class TaskDto2TaskBo extends Converter<TaskDto,TaskBo> {
    private TaskDto2TaskBo() {
    }

    public static TaskDto2TaskBo getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final TaskDto2TaskBo INSTANCE = new TaskDto2TaskBo();
    }
    @Override
    protected TaskBo doForward(TaskDto taskDto) {
        return TaskBo.builder()
                .creator(taskDto.getCreator())
                .isParallel(taskDto.getIsParallel())
                .isTiming(taskDto.getIsTiming())
                .taskDesc(taskDto.getTaskDesc())
                .taskId(taskDto.getTaskId())
                .taskName(taskDto.getTaskName())
                .testSets(taskDto.getTestSets())
                .times(taskDto.getTimes()).build();
    }

    @Override
    protected TaskDto doBackward(TaskBo taskBo) {
        return TaskDto.builder()
                .creator(taskBo.getCreator())
                .isParallel(taskBo.getIsParallel())
                .isTiming(taskBo.getIsTiming())
                .taskDesc(taskBo.getTaskDesc())
                .taskId(taskBo.getTaskId())
                .taskName(taskBo.getTaskName())
                .testSets(taskBo.getTestSets())
                .times(taskBo.getTimes()).build();
    }
}
