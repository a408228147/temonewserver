package com.creams.temo.convert;

import com.creams.temo.model.PermissionsBo;
import com.creams.temo.model.PermissionsDto;
import com.google.common.base.Converter;

public class PermissionsDto2PermissionsBo extends Converter<PermissionsDto,PermissionsBo> {

    private PermissionsDto2PermissionsBo() {
    }

    public static PermissionsDto2PermissionsBo getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final PermissionsDto2PermissionsBo INSTANCE = new PermissionsDto2PermissionsBo();
    }

    @Override
    protected PermissionsBo doForward(PermissionsDto permissionsDto) {
        if (permissionsDto==null){
            return null;
        }
        return PermissionsBo.builder()
                .id(permissionsDto.getId())
                .permissionsId(permissionsDto.getPermissionsId())
                .permissionsName(permissionsDto.getPermissionsName())
                .roleId(permissionsDto.getRoleId())
                .status(permissionsDto.getStatus()).build();
    }

    @Override
    protected PermissionsDto doBackward(PermissionsBo permissionsBo) {
        if (permissionsBo==null){
            return null;
        }
        return PermissionsDto.builder()
                .id(permissionsBo.getId())
                .permissionsId(permissionsBo.getPermissionsId())
                .permissionsName(permissionsBo.getPermissionsName())
                .roleId(permissionsBo.getRoleId())
                .status(permissionsBo.getStatus()).build();
    }
}
