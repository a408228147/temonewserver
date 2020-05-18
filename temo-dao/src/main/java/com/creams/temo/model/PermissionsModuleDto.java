package com.creams.temo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PermissionsModuleDto {
    @ApiModelProperty("id")
    private Integer id;
    @ApiModelProperty("模块名称")
    private String moduleName;
}
