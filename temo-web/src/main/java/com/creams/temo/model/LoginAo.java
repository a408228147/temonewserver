package com.creams.temo.model;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginAo {
    @ApiModelProperty("用户名称")
    private String userName;

    @ApiModelProperty("用户密码")
    private String password;
}
