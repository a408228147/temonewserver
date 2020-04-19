package com.creams.temo.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Saves {

    @ApiModelProperty("关联id")
    private String saveId;

    @ApiModelProperty("jsonpath表达式")
    private String jexpression;

    @ApiModelProperty("自定义key")
    private String paramKey;

    @ApiModelProperty("关联取值来源（响应头，响应体")
    private String saveFrom;

    @ApiModelProperty("用例id")
    private String caseId;
    
    @ApiModelProperty("关联类型")
    private String saveType;

    @ApiModelProperty("正则表达式")
    private String regex;

}
