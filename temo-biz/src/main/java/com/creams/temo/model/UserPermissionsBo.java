package com.creams.temo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserPermissionsBo {
    @ApiModelProperty("id")
    private  Integer id;

    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("权限id")
    private String permissionsId;
}
