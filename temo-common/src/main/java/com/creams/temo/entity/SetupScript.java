package com.creams.temo.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 前置脚本
 */
@Data
public class SetupScript {


    @ApiModelProperty("脚本id")
    private String scriptId;

    @ApiModelProperty("脚本类型(SET,DB)")
    private String scriptType;

}
