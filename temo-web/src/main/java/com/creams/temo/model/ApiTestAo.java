package com.creams.temo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiTestAo {

    @ApiModelProperty("状态码")
    private Integer status;

    @ApiModelProperty("响应体")
    private String responseBody;

    @ApiModelProperty("响应头")
    private String responseHeader;

    @ApiModelProperty("响应Cookie")
    private String responseCookie;
}
