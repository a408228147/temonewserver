package com.creams.temo.convert;

import com.creams.temo.model.UserRoleBo;
import com.creams.temo.model.UserRoleDto;
import com.google.common.base.Converter;

public class UserRoleDto2UserRoleBo extends Converter<UserRoleDto,UserRoleBo> {

    private UserRoleDto2UserRoleBo() {
    }

    public static UserRoleDto2UserRoleBo getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final UserRoleDto2UserRoleBo INSTANCE = new UserRoleDto2UserRoleBo();
    }
    @Override
    protected UserRoleBo doForward(UserRoleDto userRoleDto) {
        return UserRoleBo.builder()
                .id(userRoleDto.getId())
                .roleId(userRoleDto.getRoleId())
                .userId(userRoleDto.getUserId()).build();
    }

    @Override
    protected UserRoleDto doBackward(UserRoleBo userRoleBo) {
        return UserRoleDto.builder()
                .id(userRoleBo.getId())
                .roleId(userRoleBo.getRoleId())
                .userId(userRoleBo.getUserId()).build();
    }
}
