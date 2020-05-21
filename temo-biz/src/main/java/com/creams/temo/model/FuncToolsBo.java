package com.creams.temo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FuncToolsBo {
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("函数名称")
    private String funcName;

    @ApiModelProperty("函数脚本")
    private String funcScript;

    @ApiModelProperty("函数参数")
    private String funcParams;

    @ApiModelProperty("函数语言")
    private String funcLang;

    @ApiModelProperty("创建人/作者")
    private String creator;

    @ApiModelProperty("是否分享")
    private Integer isShare;

    @ApiModelProperty("函数描述")
    private String funcDescribe;

    @ApiModelProperty("创建时间")
    private String createTime;

    @ApiModelProperty("更新时间")
    private String updateTime;

}
