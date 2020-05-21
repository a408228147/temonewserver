package com.creams.temo.convert;

import com.creams.temo.model.FuncToolsBo;
import com.creams.temo.model.FuncToolsDto;
import com.google.common.base.Converter;

public class FuncToolsDto2FuncToolsBo extends Converter<FuncToolsDto,FuncToolsBo> {

    public FuncToolsDto2FuncToolsBo(){
    }
    public static FuncToolsDto2FuncToolsBo getInstance() {
        return FuncToolsDto2FuncToolsBo.SingletonHolder.INSTANCE;
    }
    private static class SingletonHolder {
        private static final FuncToolsDto2FuncToolsBo INSTANCE = new FuncToolsDto2FuncToolsBo();
    }


    @Override
    protected FuncToolsBo doForward(FuncToolsDto funcToolsdto) {
        if (funcToolsdto==null){
            return null;
        }
        return FuncToolsBo.builder()
                .createTime(funcToolsdto.getCreateTime())
                .creator(funcToolsdto.getCreator())
                .funcDescribe(funcToolsdto.getFuncDescribe())
                .funcLang(funcToolsdto.getFuncLang())
                .funcName(funcToolsdto.getFuncName())
                .funcScript(funcToolsdto.getFuncScript())
                .id(funcToolsdto.getId())
                .updateTime(funcToolsdto.getUpdateTime())
                .isShare(funcToolsdto.getIsShare())
                .funcParams(funcToolsdto.getFuncParams()).build();
    }

    @Override
    protected FuncToolsDto doBackward(FuncToolsBo funcToolsBo) {
        return FuncToolsDto.builder()
                .createTime(funcToolsBo.getCreateTime())
                .creator(funcToolsBo.getCreator())
                .funcDescribe(funcToolsBo.getFuncDescribe())
                .funcLang(funcToolsBo.getFuncLang())
                .funcName(funcToolsBo.getFuncName())
                .funcScript(funcToolsBo.getFuncScript())
                .id(funcToolsBo.getId())
                .updateTime(funcToolsBo.getUpdateTime())
                .isShare(funcToolsBo.getIsShare())
                .funcParams(funcToolsBo.getFuncParams()).build();
    }
}
