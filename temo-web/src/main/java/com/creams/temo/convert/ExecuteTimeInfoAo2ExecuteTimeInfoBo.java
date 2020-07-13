package com.creams.temo.convert;

import com.creams.temo.model.ExecuteTimeInfoAo;
import com.creams.temo.model.ExecuteTimeInfoBo;
import com.google.common.base.Converter;

public class ExecuteTimeInfoAo2ExecuteTimeInfoBo extends Converter<ExecuteTimeInfoAo,ExecuteTimeInfoBo> {
    private ExecuteTimeInfoAo2ExecuteTimeInfoBo(){}
    public static ExecuteTimeInfoAo2ExecuteTimeInfoBo getInstance() {
        return ExecuteTimeInfoAo2ExecuteTimeInfoBo.SingletonHolder.INSTANCE;
    }
    private static class SingletonHolder {
        private static final ExecuteTimeInfoAo2ExecuteTimeInfoBo INSTANCE = new ExecuteTimeInfoAo2ExecuteTimeInfoBo();
    }
    @Override
    protected ExecuteTimeInfoBo doForward(ExecuteTimeInfoAo executeTimeInfoAo) {
        if (executeTimeInfoAo==null){
            return null;
        }
        return ExecuteTimeInfoBo.builder()
                .executeTaskNumNow(executeTimeInfoAo.getExecuteTaskNumNow())
                .executeTime(executeTimeInfoAo.getExecuteTime())
                .build();
    }

    @Override
    protected ExecuteTimeInfoAo doBackward(ExecuteTimeInfoBo executeTimeInfoBo) {
        if (executeTimeInfoBo==null){
            return null;
        }
        return  ExecuteTimeInfoAo.builder()
                .executeTaskNumNow(executeTimeInfoBo.getExecuteTaskNumNow())
                .executeTime(executeTimeInfoBo.getExecuteTime())
                .build();
    }
}
