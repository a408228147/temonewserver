package com.creams.temo.convert;

import com.creams.temo.model.TesSetInfoBo;
import com.creams.temo.model.TesSetInfoDto;
import com.google.common.base.Converter;

public class TesSetInfoDto2TesSetInfoBo extends Converter<TesSetInfoDto,TesSetInfoBo> {

    private TesSetInfoDto2TesSetInfoBo() {
    }

    public static TesSetInfoDto2TesSetInfoBo getInstance() {
        return TesSetInfoDto2TesSetInfoBo.SingletonHolder.INSTANCE;
    }
    private static class SingletonHolder {
        private static final TesSetInfoDto2TesSetInfoBo INSTANCE = new TesSetInfoDto2TesSetInfoBo();
    }
    @Override
    protected TesSetInfoBo doForward(TesSetInfoDto tesSetInfoDto) {
        if (tesSetInfoDto==null){
            return null;
        }
        return TesSetInfoBo.builder()
                .testCaseNum(tesSetInfoDto.getTestCaseNum())
                .testCaseSetNum(tesSetInfoDto.getTestCaseSetNum()).build();
    }

    @Override
    protected TesSetInfoDto doBackward(TesSetInfoBo tesSetInfoBo) {
        if (tesSetInfoBo==null){
            return null;
        }
        return  TesSetInfoDto.builder()
                .testCaseNum(tesSetInfoBo.getTestCaseNum())
                .testCaseSetNum(tesSetInfoBo.getTestCaseSetNum()).build();
    }
}
