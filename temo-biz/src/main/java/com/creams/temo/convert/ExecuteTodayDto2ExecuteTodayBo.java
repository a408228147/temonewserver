package com.creams.temo.convert;

import com.creams.temo.model.ExecuteTodayBo;
import com.creams.temo.model.ExecuteTodayDto;
import com.google.common.base.Converter;

public class ExecuteTodayDto2ExecuteTodayBo extends Converter<ExecuteTodayDto,ExecuteTodayBo> {

    public ExecuteTodayDto2ExecuteTodayBo(){}
    public static ExecuteTodayDto2ExecuteTodayBo getInstance() {
        return ExecuteTodayDto2ExecuteTodayBo.SingletonHolder.INSTANCE;
    }
    private static class SingletonHolder {
        private static final ExecuteTodayDto2ExecuteTodayBo INSTANCE = new ExecuteTodayDto2ExecuteTodayBo();
    }


    @Override
    protected ExecuteTodayBo doForward(ExecuteTodayDto executeTodayDto) {
        if (executeTodayDto==null){
            return null;
        }
        return ExecuteTodayBo.builder()
                .executeCaseTodayNum(executeTodayDto.getExecuteCaseTodayNum())
                .falseNum(executeTodayDto.getFalseNum())
                .successNum(executeTodayDto.getSuccessNum())
                .successRate(executeTodayDto.getSuccessRate())
                .build();
    }

    @Override
    protected ExecuteTodayDto doBackward(ExecuteTodayBo executeTodayBo) {
        if (executeTodayBo==null){
            return null;
        }
        return  ExecuteTodayDto.builder()
                .executeCaseTodayNum(executeTodayBo.getExecuteCaseTodayNum())
                .falseNum(executeTodayBo.getFalseNum())
                .successNum(executeTodayBo.getSuccessNum())
                .successRate(executeTodayBo.getSuccessRate())
                .build();
    }
}
