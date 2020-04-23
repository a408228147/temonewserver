package com.creams.temo.biz;

import com.creams.temo.entity.ApiTestEntity;
import com.creams.temo.model.ApiTestBo;

public interface ApiTestService {

    ApiTestBo testApi(ApiTestEntity apiTestEntity);
}
