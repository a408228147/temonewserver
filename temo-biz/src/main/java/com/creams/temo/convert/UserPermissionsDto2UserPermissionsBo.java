package com.creams.temo.convert;

import com.creams.temo.model.UserPermissionsBo;
import com.creams.temo.model.UserPermissionsDto;
import com.google.common.base.Converter;

public class UserPermissionsDto2UserPermissionsBo extends Converter<UserPermissionsDto,UserPermissionsBo> {

    private UserPermissionsDto2UserPermissionsBo() {
    }

    public static UserPermissionsDto2UserPermissionsBo getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final UserPermissionsDto2UserPermissionsBo INSTANCE = new UserPermissionsDto2UserPermissionsBo();
    }

    @Override
    protected UserPermissionsBo doForward(UserPermissionsDto userPermissionsDto) {
        return UserPermissionsBo.builder()
                .id(userPermissionsDto.getId())
                .permissionsId(userPermissionsDto.getPermissionsId())
                .userId(userPermissionsDto.getUserId())
                .build();
    }

    @Override
    protected UserPermissionsDto doBackward(UserPermissionsBo userPermissionsBo) {
        return UserPermissionsDto.builder()
                .id(userPermissionsBo.getId())
                .permissionsId(userPermissionsBo.getPermissionsId())
                .userId(userPermissionsBo.getUserId()).build();
    }

}
