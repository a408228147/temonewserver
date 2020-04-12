package com.creams.temo.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserBo {
    private Integer id;

    private String userId;

    private String userName;

    private String password;

    private Integer status;
}
