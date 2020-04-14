package com.creams.temo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleBo {
    @ApiModelProperty("项目主键")
    private Integer id;


    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("角色id")
    private String roleId;


    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("角色")
    private Integer status;
}
