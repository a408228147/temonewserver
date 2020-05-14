package com.creams.temo.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FuncToolsEntity {
    @ApiModelProperty("函数名称")
    private String funcName;

    @ApiModelProperty("函数脚本")
    private String funcScript;

    @ApiModelProperty("函数参数")
    private String funcParams;

    @ApiModelProperty("函数语言")
    private String funcLang;
}
