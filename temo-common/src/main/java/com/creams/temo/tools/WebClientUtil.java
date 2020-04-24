package com.creams.temo.tools;

import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *  基于WebClient封装客户端请求工具类
 */

public class WebClientUtil {


    private WebClient webClient;
    private WebClient defaultWebclient;
    private static Logger logger = LoggerFactory.getLogger("fileInfoLog");


    public WebClientUtil(String host, String port, Map<String, String> headers, Map<String, String> cookies) throws SSLException {
        // 设置SSL
        HttpClient secure = HttpClient.create()
                .secure(t -> t.sslContext(SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE)));
        String baseUrl = port == null?host:host+":"+port;
        logger.info(String.format("开始创建webclient实例。。，设置host: %s,default headers:%s,default cookies:%s",baseUrl,
                headers.toString(),cookies.toString()));
        webClient = WebClient
                .builder()
                .clientConnector(new ReactorClientHttpConnector(secure))
                .baseUrl(baseUrl)
                .defaultHeaders(n->{
                    for (Map.Entry<String,String> entry:headers.entrySet()){
                        n.add(entry.getKey(), entry.getValue());
                    }
                })
                .defaultCookies(n->{
                    for (Map.Entry<String,String> entry:cookies.entrySet()){
                        n.add(entry.getKey(), entry.getValue());
                    }
                })
                .build();
        defaultWebclient = WebClient.create();

    }

    /**
     * get请求
     * @param url 请求地址
     * @return
     */
    public ClientResponse get(String url,Map<String,String> params,Map<String,String> headers,Map<String,String> cookies){
        StringBuilder urlBuilder = new StringBuilder(url);
        for (Map.Entry<String,String> entry:params.entrySet()){
            if (!urlBuilder.toString().contains("?")){
                urlBuilder.append("?").append(entry.getKey()).append("=").append(entry.getValue());
            }else{
                urlBuilder.append("&").append(entry.getKey()).append("=").append(entry.getValue());
            }

        }
        logger.info("开始调用接口。。");
        logger.info("========================================");
        logger.info("GET "+url);
        logger.info("Params "+params);
        logger.info("Headers  "+headers);
        logger.info("Cookies "+cookies);

        url = urlBuilder.toString();
        Mono<ClientResponse> mono  = (url.startsWith("http") | url.startsWith("https") ?defaultWebclient:webClient).get()
                .uri(url)
                .headers(n->{
                    for (Map.Entry<String,String> entry: headers.entrySet()){
                        n.add(entry.getKey(),entry.getValue());
                    }
                })
                .cookies(n->{
                    for (Map.Entry<String,String> entry: cookies.entrySet()){
                        n.add(entry.getKey(),entry.getValue());
                    }
                })
                .acceptCharset(StandardCharsets.UTF_8)
                .exchange();

        return mono.block();
    }

    /**
     * post url-en请求
     * @param url 请求地址
     * @param paramData  参数
     * @return
     */
    public ClientResponse postByFormUrlencoded(String url, MultiValueMap<String,String> paramData, Map<String,String> headers, Map<String,String> cookies){

        logger.info("开始调用接口。。");
        logger.info("========================================");
        logger.info("POST "+url);
        logger.info("paramData "+paramData);
        logger.info("Headers  "+headers);
        logger.info("Cookies "+cookies);
        // 函数式编程，遍历请求头构造参数

        Mono<ClientResponse> mono = (url.startsWith("http") | url.startsWith("https") ?defaultWebclient:webClient).post().uri(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(paramData))
                .headers(n->{
                    for (Map.Entry<String,String> entry:headers.entrySet()){
                        n.add(entry.getKey(),entry.getValue());
                    }
                })
                .cookies(n-> {
                    for (Map.Entry<String, String> entry : cookies.entrySet()) {
                        n.add(entry.getKey(), entry.getValue());
                    }
                })
                .acceptCharset(StandardCharsets.UTF_8)
                .exchange();
        return mono.block();
    }

    /**
     * post 表单请求
     * @param url 请求地址
     * @param formData  表单参数
     * @return
     */
    public ClientResponse postByFormData(String url, MultiValueMap<String,String> formData, Map<String,String> headers, Map<String,String> cookies){

        logger.info("开始调用接口。。");
        logger.info("========================================");
        logger.info("POST "+url);
        logger.info("FormData "+formData);
        logger.info("Headers  "+headers);
        logger.info("Cookies "+cookies);
        // 函数式编程，遍历请求头构造参数

        Mono<ClientResponse> mono = (url.startsWith("http") | url.startsWith("https") ?defaultWebclient:webClient).post().uri(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(formData))
                .headers(n->{
                    for (Map.Entry<String,String> entry:headers.entrySet()){
                        n.add(entry.getKey(),entry.getValue());
                    }
                })
                .cookies(n-> {
                    for (Map.Entry<String, String> entry : cookies.entrySet()) {
                        n.add(entry.getKey(), entry.getValue());
                    }
                })
                .acceptCharset(StandardCharsets.UTF_8)
                .exchange();
        return mono.block();
    }

    /**
     *  post Json请求
     * @param url 请求地址
     * @param json  json字符串
     * @return
     */
    public ClientResponse postByJson(String url, String json,Map<String,String> headers,Map<String,String> cookies){
        logger.info("开始调用接口。。");
        logger.info("========================================");
        logger.info("POST "+url);
        logger.info("Body "+json);
        logger.info("Headers  "+headers);
        logger.info("Cookies "+cookies);
        // 函数式编程，遍历请求头构造参数
        Mono<ClientResponse> mono = (url.startsWith("http") | url.startsWith("https") ?defaultWebclient:webClient).post().uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(json))
                .headers(n->{
                    for (Map.Entry<String,String> entry:headers.entrySet()){
                        n.add(entry.getKey(),entry.getValue());
                    }
                })
                .cookies(n-> {
                    for (Map.Entry<String, String> entry : cookies.entrySet()) {
                        n.add(entry.getKey(), entry.getValue());
                    }
                })
                .acceptCharset(StandardCharsets.UTF_8)
                .exchange();
        return mono.block();
    }

    /**
     *  put请求
     * @param url 请求地址
     * @param json  json字符串
     * @return
     */
    public ClientResponse put(String url, String json,Map<String,String> headers,Map<String,String> cookies){
        logger.info("开始调用接口。。");
        logger.info("========================================");
        logger.info("PUT "+url);
        logger.info("Body "+json);
        logger.info("Headers  "+headers);
        logger.info("Cookies "+cookies);
        // 函数式编程，遍历请求头构造参数
        Mono<ClientResponse> mono = (url.startsWith("http") | url.startsWith("https") ?defaultWebclient:webClient).put().uri(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(json))
                .headers(n->{
                    for (Map.Entry<String,String> entry:headers.entrySet()){
                        n.add(entry.getKey(),entry.getValue());
                    }
                })
                .cookies(n-> {
                    for (Map.Entry<String, String> entry : cookies.entrySet()) {
                        n.add(entry.getKey(), entry.getValue());
                    }
                })
                .acceptCharset(StandardCharsets.UTF_8)
                .exchange();

        return mono.block();
    }

    /**
     *  delete请求
     * @param url 请求地址
     * @return
     */
    public ClientResponse delete(String url, Map<String,String> headers, Map<String,String> cookies){
        logger.info("开始调用接口。。");
        logger.info("========================================");
        logger.info("DELETE "+url);
        logger.info("Headers  "+headers);
        logger.info("Cookies "+cookies);
        // 函数式编程，遍历请求头构造参数
        Mono<ClientResponse> mono = (url.startsWith("http") | url.startsWith("https") ?defaultWebclient:webClient).delete().uri(url)
                .headers(n->{
                    for (Map.Entry<String,String> entry:headers.entrySet()){
                        n.add(entry.getKey(),entry.getValue());
                    }
                })
                .cookies(n-> {
                    for (Map.Entry<String, String> entry : cookies.entrySet()) {
                        n.add(entry.getKey(), entry.getValue());
                    }
                })
                .acceptCharset(StandardCharsets.UTF_8)
                .exchange();

        return mono.block();
    }

//    public static void main(String[] args) throws SSLException {
//        String url = "https://www.baidu.com";
//        ClientResponse clientResponse =  WebClient.create().get().uri(url).exchange().block();
//        assert clientResponse != null;
//        System.out.println(clientResponse.bodyToMono(String.class).block());
//        System.out.println(clientResponse.cookies());
//        System.out.println(clientResponse.headers());
//        WebClientUtil webClientUtil = new WebClientUtil("xxx","12",new HashMap<>(),new HashMap<>());
//        webClientUtil.get(url,new HashMap<>(),new HashMap<>(),new HashMap<>());

//    }
}
