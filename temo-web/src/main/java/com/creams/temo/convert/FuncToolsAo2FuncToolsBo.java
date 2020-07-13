package com.creams.temo.convert;

import com.creams.temo.model.FuncToolsAo;
import com.creams.temo.model.FuncToolsBo;
import com.google.common.base.Converter;

public class FuncToolsAo2FuncToolsBo extends Converter<FuncToolsAo,FuncToolsBo> {

    private FuncToolsAo2FuncToolsBo(){
    }
    public static FuncToolsAo2FuncToolsBo getInstance() {
        return FuncToolsAo2FuncToolsBo.SingletonHolder.INSTANCE;
    }
    private static class SingletonHolder {
        private static final FuncToolsAo2FuncToolsBo INSTANCE = new FuncToolsAo2FuncToolsBo();
    }


    @Override
    protected FuncToolsBo doForward(FuncToolsAo funcToolsAo) {
        if (funcToolsAo==null){
            return null;
        }
        return FuncToolsBo.builder()
                .createTime(funcToolsAo.getCreateTime())
                .creator(funcToolsAo.getCreator())
                .funcDescribe(funcToolsAo.getFuncDescribe())
                .funcLang(funcToolsAo.getFuncLang())
                .funcName(funcToolsAo.getFuncName())
                .funcScript(funcToolsAo.getFuncScript())
                .id(funcToolsAo.getId())
                .updateTime(funcToolsAo.getUpdateTime())
                .isShare(funcToolsAo.getIsShare())
                .funcParams(funcToolsAo.getFuncParams()).build();
    }

    @Override
    protected FuncToolsAo doBackward(FuncToolsBo funcToolsBo) {
        return FuncToolsAo.builder()
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
