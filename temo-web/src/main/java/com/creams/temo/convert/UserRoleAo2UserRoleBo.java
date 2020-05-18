package com.creams.temo.convert;

import com.creams.temo.model.UserRoleAo;
import com.creams.temo.model.UserRoleBo;
import com.google.common.base.Converter;

public class UserRoleAo2UserRoleBo extends Converter<UserRoleAo,UserRoleBo> {

    private UserRoleAo2UserRoleBo() {
    }

    public static UserRoleAo2UserRoleBo getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final UserRoleAo2UserRoleBo INSTANCE = new UserRoleAo2UserRoleBo();
    }
    @Override
    protected UserRoleBo doForward(UserRoleAo userRoleAo) {
        return UserRoleBo.builder()
                .id(userRoleAo.getId())
                .roleId(userRoleAo.getRoleId())
                .userId(userRoleAo.getUserId()).build();
    }

    @Override
    protected UserRoleAo doBackward(UserRoleBo userRoleBo) {
        return UserRoleAo.builder()
                .id(userRoleBo.getId())
                .roleId(userRoleBo.getRoleId())
                .userId(userRoleBo.getUserId()).build();
    }
}
