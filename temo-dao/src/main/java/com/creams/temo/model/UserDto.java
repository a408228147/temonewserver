package com.creams.temo.model;

import lombok.Data;

@Data
public class UserDto {
    private Integer id;

    private String userId;

    private String userName;

    private String password;

    private Integer status;
}
