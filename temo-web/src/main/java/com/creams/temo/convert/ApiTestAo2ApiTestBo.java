package com.creams.temo.convert;



import com.creams.temo.model.ApiTestAo;
import com.creams.temo.model.ApiTestBo;
import com.google.common.base.Converter;

public class ApiTestAo2ApiTestBo extends Converter<ApiTestAo, ApiTestBo> {

    public ApiTestAo2ApiTestBo(){}

    public static ApiTestAo2ApiTestBo getInstance() {
        return ApiTestAo2ApiTestBo.SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final ApiTestAo2ApiTestBo INSTANCE = new ApiTestAo2ApiTestBo();
    }

    @Override
    protected ApiTestBo doForward(ApiTestAo apiTestAo) {
        if (apiTestAo == null){
            return null;
        }
        return ApiTestBo.builder()
                .status(apiTestAo.getStatus())
                .responseBody(apiTestAo.getResponseBody())
                .responseCookie(apiTestAo.getResponseCookie())
                .responseHeader(apiTestAo.getResponseHeader())
                .build();
    }

    @Override
    protected ApiTestAo doBackward(ApiTestBo apiTestBo) {
        if (apiTestBo == null){
            return null;
        }
        return ApiTestAo.builder()
                .status(apiTestBo.getStatus())
                .responseBody(apiTestBo.getResponseBody())
                .responseCookie(apiTestBo.getResponseCookie())
                .responseHeader(apiTestBo.getResponseHeader())
                .build();
    }
}
