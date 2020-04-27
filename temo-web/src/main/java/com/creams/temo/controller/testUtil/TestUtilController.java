package com.creams.temo.controller.testUtil;

import com.creams.temo.biz.ApiTestService;
import com.creams.temo.biz.PermissionsService;
import com.creams.temo.convert.ApiTestAo2ApiTestBo;
import com.creams.temo.convert.PermissionsAo2PermissionsBo;
import com.creams.temo.entity.ApiTestEntity;
import com.creams.temo.model.ApiTestBo;
import com.creams.temo.result.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.net.ssl.SSLException;


@RestController
@Api("TestUtilController APi")
@RequestMapping("/testUtil")
public class TestUtilController {

    @Autowired
    ApiTestService apiTestService;

    final ApiTestAo2ApiTestBo apiTestAo2ApiTestBo = ApiTestAo2ApiTestBo.getInstance();

    @ApiOperation("调试接口")
    @PostMapping("/testApi")
    public JsonResult testApi(@RequestBody ApiTestEntity apiTestEntity) throws SSLException {
        try {
            ApiTestBo apiTestBo = apiTestService.testApi(apiTestEntity);
            return new JsonResult("调试成功",200,apiTestAo2ApiTestBo.reverse().convert(apiTestBo),true);
        }catch (Exception e){
            return new JsonResult("调试失败",500,e.getMessage(),false);
        }

    }
}
