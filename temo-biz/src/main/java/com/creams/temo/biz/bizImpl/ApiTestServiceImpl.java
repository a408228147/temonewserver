package com.creams.temo.biz.bizImpl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.creams.temo.biz.ApiTestService;
import com.creams.temo.entity.ApiTestEntity;
import com.creams.temo.model.ApiTestBo;
import com.creams.temo.tools.WebClientUtil;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.reactive.function.client.ClientResponse;

import javax.net.ssl.SSLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ApiTestServiceImpl implements ApiTestService {

    @Override
    public ApiTestBo testApi(ApiTestEntity apiTestEntity) throws SSLException {
        String url = apiTestEntity.getUrl();
        String method = apiTestEntity.getMethod().toUpperCase();
        Integer contentType = apiTestEntity.getContentType();
        Map<String,String> body = JSON.parseObject(apiTestEntity.getBody(), new TypeReference<Map<String, String>>() {});
        Map<String,String> params = JSON.parseObject(apiTestEntity.getParam(), new TypeReference<Map<String, String>>() {});
        Map<String,String> cookies = JSON.parseObject(apiTestEntity.getCookie(), new TypeReference<Map<String, String>>() {});
        Map<String,String> headers = JSON.parseObject(apiTestEntity.getHeader(), new TypeReference<Map<String, String>>() {});
        WebClientUtil webClientUtil = new WebClientUtil(null,null,new HashMap<>(),new HashMap<>());
        ClientResponse clientResponse;
        if ("GET".equals(method)){
            clientResponse = webClientUtil.get(url,params,headers,cookies);
        }else if ("POST".equals(method)){
            LinkedMultiValueMap<String, String> linkedMultiValueMap = new LinkedMultiValueMap<>();
            for (Map.Entry<String, String> kvs : body.entrySet()) {
                linkedMultiValueMap.add(kvs.getKey(), kvs.getValue());
            }
            if (contentType == 1){
                clientResponse = webClientUtil.postByFormUrlencoded(url,linkedMultiValueMap,headers,cookies);
            }else if (contentType == 2){
                clientResponse = webClientUtil.postByFormData(url,linkedMultiValueMap,headers,cookies);
            }else {
                clientResponse = webClientUtil.postByJson(url,apiTestEntity.getBody(),headers,cookies);
            }
        }else if ("PUT".equals(method)){
            clientResponse = webClientUtil.put(url,apiTestEntity.getBody(),headers,cookies);
        }else {
            clientResponse = webClientUtil.delete(url,headers,cookies);
        }
        Map<String, Object> rHeaders = new HashMap<>();
        // 处理响应头，转换为JSON字符串
        for (Map.Entry<String, List<String>> entry : clientResponse.headers().asHttpHeaders().entrySet()) {
            rHeaders.put(entry.getKey(), entry.getValue());
        }
        Map<String, Object> rCookies = new HashMap<>();
        for (Map.Entry<String, List<ResponseCookie>> entry : clientResponse.cookies().entrySet()) {
            rCookies.put(entry.getKey(), entry.getValue());
        }
        return ApiTestBo.builder()
                .status(clientResponse.statusCode().value())
                .responseBody(clientResponse.bodyToMono(String.class).block())
                .responseHeader(new JSONObject(rHeaders).toString())
                .responseCookie(new JSONObject(rCookies).toString())
                .build();
    }
}
