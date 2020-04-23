package com.creams.temo.entity;


import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiTestEntity {

    @ApiModelProperty(value = "url")
    private String url;

    @ApiModelProperty(value = "请求参数")
    private String param;

    @ApiModelProperty(value = "请求体")
    private String body;

    @ApiModelProperty(value = "请求方式")
    private String method;

    @ApiModelProperty(value = "请求头")
    private String header;

    @ApiModelProperty(value = "cookie")
    private String cookie;

    @ApiModelProperty(value = "请求正文")
    private Integer contentType;

}
