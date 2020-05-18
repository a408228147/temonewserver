package com.creams.temo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RolePermissionsBo {
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("角色id")
    private String roleId;
    
    @ApiModelProperty("权限id")
    private Integer permissionsId;
}
