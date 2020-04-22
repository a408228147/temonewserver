package com.creams.temo.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DingdingEntity {

    @ApiModelProperty(value = "项目主键", hidden = true)
    private Integer id;

    @ApiModelProperty(value = "描述id")
    private String descId;

    @ApiModelProperty("描述")
    private String desc;

    @ApiModelProperty("关键字")
    private String webhook;

    @ApiModelProperty("关键字")
    private String keysWord;

    @ApiModelProperty(value = "创建人", hidden = true)
    private String creater;

}
