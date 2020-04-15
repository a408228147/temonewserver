package com.creams.temo.convert;

import com.creams.temo.model.ExecuteTodayAo;
import com.creams.temo.model.ExecuteTodayBo;
import com.google.common.base.Converter;

public class ExecuteTodayAo2ExecuteTodayBo extends Converter<ExecuteTodayAo,ExecuteTodayBo> {


    public ExecuteTodayAo2ExecuteTodayBo(){}
    public static ExecuteTodayAo2ExecuteTodayBo getInstance() {
        return ExecuteTodayAo2ExecuteTodayBo.SingletonHolder.INSTANCE;
    }
    private static class SingletonHolder {
        private static final ExecuteTodayAo2ExecuteTodayBo INSTANCE = new ExecuteTodayAo2ExecuteTodayBo();
    }

    @Override
    protected ExecuteTodayBo doForward(ExecuteTodayAo executeTodayAo) {
        return ExecuteTodayBo.builder()
                .executeCaseTodayNum(executeTodayAo.getExecuteCaseTodayNum())
                .falseNum(executeTodayAo.getFalseNum())
                .successNum(executeTodayAo.getSuccessNum())
                .successRate(executeTodayAo.getSuccessRate())
                .build();
    }

    @Override
    protected ExecuteTodayAo doBackward(ExecuteTodayBo executeTodayBo) {
        return  ExecuteTodayAo.builder()
                .executeCaseTodayNum(executeTodayBo.getExecuteCaseTodayNum())
                .falseNum(executeTodayBo.getFalseNum())
                .successNum(executeTodayBo.getSuccessNum())
                .successRate(executeTodayBo.getSuccessRate())
                .build();
    }
}
