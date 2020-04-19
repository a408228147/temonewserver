package com.creams.temo.convert;

import com.creams.temo.model.TimingTaskBo;
import com.creams.temo.model.TimingTaskDto;
import com.google.common.base.Converter;

public class TimingTaskDto2TimingTaskBo extends Converter<TimingTaskDto,TimingTaskBo> {

    private TimingTaskDto2TimingTaskBo() {
    }

    public static TimingTaskDto2TimingTaskBo getInstance() {
        return SingletonHolder.INSTANCE;
    }
    private static class SingletonHolder {
        private static final TimingTaskDto2TimingTaskBo INSTANCE = new TimingTaskDto2TimingTaskBo();
    }
    @Override
    protected TimingTaskBo doForward(TimingTaskDto timingTaskDto) {
        if (timingTaskDto==null){
            return null;
        }
        return TimingTaskBo.builder()
                .createTime(timingTaskDto.getCreateTime())
                .creator(timingTaskDto.getCreator())
                .cron(timingTaskDto.getCron())
                .isMail(timingTaskDto.getIsMail())
                .isOpen(timingTaskDto.getIsOpen())
                .isParallel(timingTaskDto.getIsParallel())
                .mail(timingTaskDto.getMail())
                .taskDesc(timingTaskDto.getTaskDesc())
                .taskId(timingTaskDto.getTaskId())
                .taskName(timingTaskDto.getTaskName())
                .testSetList(timingTaskDto.getTestSetList())
                .testSets(timingTaskDto.getTestSets())
                .times(timingTaskDto.getTimes())
                .updateTime(timingTaskDto.getUpdateTime()).build();
    }

    @Override
    protected TimingTaskDto doBackward(TimingTaskBo timingTaskBo) {
        if (timingTaskBo==null){
            return null;
        }
        return TimingTaskDto.builder()
                .createTime(timingTaskBo.getCreateTime())
                .creator(timingTaskBo.getCreator())
                .cron(timingTaskBo.getCron())
                .isMail(timingTaskBo.getIsMail())
                .isOpen(timingTaskBo.getIsOpen())
                .isParallel(timingTaskBo.getIsParallel())
                .mail(timingTaskBo.getMail())
                .taskDesc(timingTaskBo.getTaskDesc())
                .taskId(timingTaskBo.getTaskId())
                .taskName(timingTaskBo.getTaskName())
                .testSetList(timingTaskBo.getTestSetList())
                .testSets(timingTaskBo.getTestSets())
                .times(timingTaskBo.getTimes())
                .updateTime(timingTaskBo.getUpdateTime()).build();
    }
}
