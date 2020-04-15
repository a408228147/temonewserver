package com.creams.temo.convert;

import com.creams.temo.model.TesSetInfoAo;
import com.creams.temo.model.TesSetInfoBo;
import com.google.common.base.Converter;

public class TesSetInfoAo2TesSetInfoBo extends Converter<TesSetInfoAo,TesSetInfoBo> {

    private TesSetInfoAo2TesSetInfoBo() {
    }

    public static TesSetInfoAo2TesSetInfoBo getInstance() {
        return TesSetInfoAo2TesSetInfoBo.SingletonHolder.INSTANCE;
    }
    private static class SingletonHolder {
        private static final TesSetInfoAo2TesSetInfoBo INSTANCE = new TesSetInfoAo2TesSetInfoBo();
    }
    @Override
    protected TesSetInfoBo doForward(TesSetInfoAo tesSetInfoAo) {
        return TesSetInfoBo.builder()
                .testCaseNum(tesSetInfoAo.getTestCaseNum())
                .testCaseSetNum(tesSetInfoAo.getTestCaseSetNum()).build();
    }

    @Override
    protected TesSetInfoAo doBackward(TesSetInfoBo tesSetInfoBo) {
        return  TesSetInfoAo.builder()
                .testCaseNum(tesSetInfoBo.getTestCaseNum())
                .testCaseSetNum(tesSetInfoBo.getTestCaseSetNum()).build();
    }
}
