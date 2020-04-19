package com.creams.temo.convert;

import com.creams.temo.model.TimingTaskAo;
import com.creams.temo.model.TimingTaskBo;
import com.google.common.base.Converter;

public class TimingTaskAo2TimingTaskBo extends Converter<TimingTaskAo,TimingTaskBo> {

    private TimingTaskAo2TimingTaskBo() {
    }

    public static TimingTaskAo2TimingTaskBo getInstance() {
        return TimingTaskAo2TimingTaskBo.SingletonHolder.INSTANCE;
    }
    private static class SingletonHolder {
        private static final TimingTaskAo2TimingTaskBo INSTANCE = new TimingTaskAo2TimingTaskBo();
    }
    @Override
    protected TimingTaskBo doForward(TimingTaskAo timingTaskAo) {
        return TimingTaskBo.builder()
                .createTime(timingTaskAo.getCreateTime())
                .creator(timingTaskAo.getCreator())
                .cron(timingTaskAo.getCron())
                .isMail(timingTaskAo.getIsMail())
                .isOpen(timingTaskAo.getIsOpen())
                .isParallel(timingTaskAo.getIsParallel())
                .mail(timingTaskAo.getMail())
                .taskDesc(timingTaskAo.getTaskDesc())
                .taskId(timingTaskAo.getTaskId())
                .taskName(timingTaskAo.getTaskName())
                .testSetList(timingTaskAo.getTestSetList())
                .testSets(timingTaskAo.getTestSets())
                .times(timingTaskAo.getTimes())
                .updateTime(timingTaskAo.getUpdateTime()).build();
    }

    @Override
    protected TimingTaskAo doBackward(TimingTaskBo timingTaskBo) {
        return TimingTaskAo.builder()
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
