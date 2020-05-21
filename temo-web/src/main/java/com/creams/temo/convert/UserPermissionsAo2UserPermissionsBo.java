package com.creams.temo.convert;

import com.creams.temo.model.UserPermissionsAo;
import com.creams.temo.model.UserPermissionsBo;
import com.creams.temo.model.UserPermissionsDto;
import com.google.common.base.Converter;

public class UserPermissionsAo2UserPermissionsBo extends Converter<UserPermissionsAo,UserPermissionsBo> {

    private UserPermissionsAo2UserPermissionsBo() {
    }

    public static UserPermissionsAo2UserPermissionsBo getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final UserPermissionsAo2UserPermissionsBo INSTANCE = new UserPermissionsAo2UserPermissionsBo();
    }

    @Override
    protected UserPermissionsBo doForward(UserPermissionsAo userPermissionsAo) {
        return UserPermissionsBo.builder()
                .id(userPermissionsAo.getId())
                .permissionsId(userPermissionsAo.getPermissionsId())
                .userId(userPermissionsAo.getUserId())
                .build();
    }

    @Override
    protected UserPermissionsAo doBackward(UserPermissionsBo userPermissionsBo) {
        return UserPermissionsAo.builder()
                .id(userPermissionsBo.getId())
                .permissionsId(userPermissionsBo.getPermissionsId())
                .userId(userPermissionsBo.getUserId()).build();
    }

}
