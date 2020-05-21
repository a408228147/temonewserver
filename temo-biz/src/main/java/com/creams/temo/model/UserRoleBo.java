package com.creams.temo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserRoleBo {
    @ApiModelProperty("id")
    private  Integer id;

    @ApiModelProperty("用户id")
    private  String userId;

    @ApiModelProperty("角色id")
    private String roleId;
}
