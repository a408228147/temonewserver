package com.creams.temo.biz;

import com.creams.temo.entity.ApiTestEntity;
import com.creams.temo.model.ApiTestBo;

import javax.net.ssl.SSLException;

public interface ApiTestService {

    ApiTestBo testApi(ApiTestEntity apiTestEntity) throws SSLException;
}
