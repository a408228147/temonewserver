package com.creams.temo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * 接口调试结果
 */
@Data
@Builder
public class ApiTestBo {

    @ApiModelProperty("状态码")
    private Integer status;

    @ApiModelProperty("响应体")
    private String responseBody;

    @ApiModelProperty("响应头")
    private String responseHeader;

    @ApiModelProperty("响应Cookie")
    private String responseCookie;
}
