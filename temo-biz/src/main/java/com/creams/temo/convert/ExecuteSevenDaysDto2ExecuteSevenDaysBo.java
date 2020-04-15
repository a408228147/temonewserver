package com.creams.temo.convert;

import com.creams.temo.model.ExecuteSevenDaysBo;
import com.creams.temo.model.ExecuteSevenDaysDto;
import com.google.common.base.Converter;

public class ExecuteSevenDaysDto2ExecuteSevenDaysBo extends Converter<ExecuteSevenDaysDto,ExecuteSevenDaysBo> {

    public ExecuteSevenDaysDto2ExecuteSevenDaysBo(){}
    public static ExecuteSevenDaysDto2ExecuteSevenDaysBo getInstance() {
        return ExecuteSevenDaysDto2ExecuteSevenDaysBo.SingletonHolder.INSTANCE;
    }
    private static class SingletonHolder {
        private static final ExecuteSevenDaysDto2ExecuteSevenDaysBo INSTANCE = new ExecuteSevenDaysDto2ExecuteSevenDaysBo();
    }
    @Override
    protected ExecuteSevenDaysBo doForward(ExecuteSevenDaysDto executeSevenDaysDto) {
        return ExecuteSevenDaysBo.builder()
                .falseNum(executeSevenDaysDto.getFalseNum())
                .successNum(executeSevenDaysDto.getSuccessNum())
                .successRate(executeSevenDaysDto.getSuccessRate()).build();
    }

    @Override
    protected ExecuteSevenDaysDto doBackward(ExecuteSevenDaysBo executeSevenDaysBo) {
        return ExecuteSevenDaysDto.builder()
                .falseNum(executeSevenDaysBo.getFalseNum())
                .successNum(executeSevenDaysBo.getSuccessNum())
                .successRate(executeSevenDaysBo.getSuccessRate()).build();

    }
}
