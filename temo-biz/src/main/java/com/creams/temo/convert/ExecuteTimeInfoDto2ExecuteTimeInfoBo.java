package com.creams.temo.convert;

import com.creams.temo.model.ExecuteTimeInfoBo;
import com.creams.temo.model.ExecuteTimeInfoDto;
import com.google.common.base.Converter;

public class ExecuteTimeInfoDto2ExecuteTimeInfoBo extends Converter<ExecuteTimeInfoDto,ExecuteTimeInfoBo> {
    public ExecuteTimeInfoDto2ExecuteTimeInfoBo(){}
    public static ExecuteTimeInfoDto2ExecuteTimeInfoBo getInstance() {
        return ExecuteTimeInfoDto2ExecuteTimeInfoBo.SingletonHolder.INSTANCE;
    }
    private static class SingletonHolder {
        private static final ExecuteTimeInfoDto2ExecuteTimeInfoBo INSTANCE = new ExecuteTimeInfoDto2ExecuteTimeInfoBo();
    }
    @Override
    protected ExecuteTimeInfoBo doForward(ExecuteTimeInfoDto executeTimeInfoDto) {
        return ExecuteTimeInfoBo.builder()
                .executeTaskNumNow(executeTimeInfoDto.getExecuteTaskNumNow())
                .executeTime(executeTimeInfoDto.getExecuteTime())
                .build();
    }

    @Override
    protected ExecuteTimeInfoDto doBackward(ExecuteTimeInfoBo executeTimeInfoBo) {
        return  ExecuteTimeInfoDto.builder()
                .executeTaskNumNow(executeTimeInfoBo.getExecuteTaskNumNow())
                .executeTime(executeTimeInfoBo.getExecuteTime())
                .build();
    }
}
