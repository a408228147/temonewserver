package com.creams.temo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ScriptDbAo {
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("脚本id")
    private String scriptId;

    @ApiModelProperty("脚本名称")
    private String scriptName;

    @ApiModelProperty("数据库id")
    private String dbId;

    @ApiModelProperty("sql脚本")
    private String sqlScript;

    @ApiModelProperty("数据库详情")
    private DatabaseAo Db;


    @ApiModelProperty("创建时间")
    private String createTime;

    @ApiModelProperty("修改时间")
    private String updateTime;


}
