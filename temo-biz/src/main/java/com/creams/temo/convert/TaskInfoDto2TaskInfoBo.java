package com.creams.temo.convert;

import com.creams.temo.model.TaskInfoDto;
import com.creams.temo.model.TaskInfoBo;
import com.google.common.base.Converter;

public class TaskInfoDto2TaskInfoBo extends Converter<TaskInfoDto,TaskInfoBo> {


    private TaskInfoDto2TaskInfoBo() {
    }
    public static TaskInfoDto2TaskInfoBo getInstance() {
        return TaskInfoDto2TaskInfoBo.SingletonHolder.INSTANCE;
    }
    private static class SingletonHolder {
        private static final TaskInfoDto2TaskInfoBo INSTANCE = new TaskInfoDto2TaskInfoBo();
    }
    @Override
    protected TaskInfoBo doForward(TaskInfoDto taskInfoDto) {
        return TaskInfoBo.builder()
                .taskIsEndNum(taskInfoDto.getTaskIsEndNum())
                .taskIsStartNum(taskInfoDto.getTaskIsStartNum())
                .taskIsTimingNum(taskInfoDto.getTaskIsTimingNum())
                .taskNum(taskInfoDto.getTaskNum()).build();
    }

    @Override
    protected TaskInfoDto doBackward(TaskInfoBo taskInfoBo) {
        return TaskInfoDto.builder()
                .taskIsEndNum(taskInfoBo.getTaskIsEndNum())
                .taskIsStartNum(taskInfoBo.getTaskIsStartNum())
                .taskIsTimingNum(taskInfoBo.getTaskIsTimingNum())
                .taskNum(taskInfoBo.getTaskNum()).build();
    }
}
