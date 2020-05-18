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
        return PermissionsBo.builder().status(permissionsDto.getStatus())
                .permissionsName(permissionsDto.getPermissionsName())
                .id(permissionsDto.getId())
                .permissionsId(permissionsDto.getPermissionsId())
                .moduleId(permissionsDto.getModuleId())
                .permissionsRoute(permissionsDto.getPermissionsRoute())
                .build();
    }

    @Override
    protected PermissionsDto doBackward(PermissionsBo permissionsBo) {
        if (permissionsBo==null){
            return null;
        }
        return PermissionsDto.builder().status(permissionsBo.getStatus())
                .permissionsName(permissionsBo.getPermissionsName())
                .id(permissionsBo.getId())
                .permissionsId(permissionsBo.getPermissionsId())
                .moduleId(permissionsBo.getModuleId())
                .permissionsRoute(permissionsBo.getPermissionsRoute())
                .build();
    }
}
