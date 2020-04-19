package com.creams.temo.convert;

import com.creams.temo.model.TaskAo;
import com.creams.temo.model.TaskBo;
import com.google.common.base.Converter;

public class TaskAo2TaskBo extends Converter<TaskAo,TaskBo> {
    private TaskAo2TaskBo() {
    }

    public static TaskAo2TaskBo getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final TaskAo2TaskBo INSTANCE = new TaskAo2TaskBo();
    }
    @Override
    protected TaskBo doForward(TaskAo taskAo) {
        if (taskAo==null){
            return null;
        }
        return TaskBo.builder()
                .creator(taskAo.getCreator())
                .isParallel(taskAo.getIsParallel())
                .isTiming(taskAo.getIsTiming())
                .taskDesc(taskAo.getTaskDesc())
                .taskId(taskAo.getTaskId())
                .taskName(taskAo.getTaskName())
                .testSets(taskAo.getTestSets())
                .times(taskAo.getTimes()).build();
    }

    @Override
    protected TaskAo doBackward(TaskBo taskBo) {
        if (taskBo==null){
            return null;
        }
        return TaskAo.builder()
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
