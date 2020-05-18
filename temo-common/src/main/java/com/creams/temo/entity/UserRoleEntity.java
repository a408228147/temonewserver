package com.creams.temo.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
/**
 * user_role role 连表bean
 */
public class UserRoleEntity {
    @ApiModelProperty("user_role-id")
    private Integer id;

    @ApiModelProperty("user_role-user_id")
    private String userId;

    @ApiModelProperty("role-role_id")
    private String roleId;

    @ApiModelProperty("role-role_name")
    private String roleName;
}
