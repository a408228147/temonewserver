package com.creams.temo.convert;

import com.creams.temo.model.ExecuteSevenDaysAo;
import com.creams.temo.model.ExecuteSevenDaysBo;
import com.google.common.base.Converter;

public class ExecuteSevenDaysAo2ExecuteSevenDaysBo extends Converter<ExecuteSevenDaysAo,ExecuteSevenDaysBo> {

    private ExecuteSevenDaysAo2ExecuteSevenDaysBo(){}
    public static ExecuteSevenDaysAo2ExecuteSevenDaysBo getInstance() {
        return ExecuteSevenDaysAo2ExecuteSevenDaysBo.SingletonHolder.INSTANCE;
    }
    private static class SingletonHolder {
        private static final ExecuteSevenDaysAo2ExecuteSevenDaysBo INSTANCE = new ExecuteSevenDaysAo2ExecuteSevenDaysBo();
    }
    @Override
    protected ExecuteSevenDaysBo doForward(ExecuteSevenDaysAo executeSevenDaysAo) {
        if (executeSevenDaysAo==null){
            return null;
        }
        return ExecuteSevenDaysBo.builder()
                .falseNum(executeSevenDaysAo.getFalseNum())
                .successNum(executeSevenDaysAo.getSuccessNum())
                .successRate(executeSevenDaysAo.getSuccessRate()).build();
    }

    @Override
    protected ExecuteSevenDaysAo doBackward(ExecuteSevenDaysBo executeSevenDaysBo) {
        if (executeSevenDaysBo==null){
            return null;
        }
        return ExecuteSevenDaysAo.builder()
                .falseNum(executeSevenDaysBo.getFalseNum())
                .successNum(executeSevenDaysBo.getSuccessNum())
                .successRate(executeSevenDaysBo.getSuccessRate()).build();

    }
}
