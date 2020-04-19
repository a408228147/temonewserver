package com.creams.temo.convert;

import com.creams.temo.model.TaskInfoAo;
import com.creams.temo.model.TaskInfoBo;
import com.google.common.base.Converter;

public class TaskInfoAo2TaskInfoBo extends Converter<TaskInfoAo,TaskInfoBo> {


    private TaskInfoAo2TaskInfoBo() {
    }
    public static TaskInfoAo2TaskInfoBo getInstance() {
        return TaskInfoAo2TaskInfoBo.SingletonHolder.INSTANCE;
    }
    private static class SingletonHolder {
        private static final TaskInfoAo2TaskInfoBo INSTANCE = new TaskInfoAo2TaskInfoBo();
    }
    @Override
    protected TaskInfoBo doForward(TaskInfoAo taskInfoAo) {
        if (taskInfoAo==null){
            return null;
        }
        return TaskInfoBo.builder()
                .taskIsEndNum(taskInfoAo.getTaskIsEndNum())
                .taskIsStartNum(taskInfoAo.getTaskIsStartNum())
                .taskIsTimingNum(taskInfoAo.getTaskIsTimingNum())
                .taskNum(taskInfoAo.getTaskNum()).build();
    }

    @Override
    protected TaskInfoAo doBackward(TaskInfoBo taskInfoBo) {
        if (taskInfoBo==null){
            return null;
        }
        return TaskInfoAo.builder()
                .taskIsEndNum(taskInfoBo.getTaskIsEndNum())
                .taskIsStartNum(taskInfoBo.getTaskIsStartNum())
                .taskIsTimingNum(taskInfoBo.getTaskIsTimingNum())
                .taskNum(taskInfoBo.getTaskNum()).build();
    }
}
