package com.creams.temo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.time.DurationMax;

@Data
@Builder
public class PermissionsDto {
    @ApiModelProperty("项目主键")
    private Integer id;

    @ApiModelProperty("权限id")
    private String permissionsId;

    @ApiModelProperty("权限名称")
    private String permissionsName;

    @ApiModelProperty("权限路由")
    private String permissionsRoute;

    @ApiModelProperty("权限状态")
    private Integer status;

    @ApiModelProperty("权限模块id")
    private Integer moduleId;
}