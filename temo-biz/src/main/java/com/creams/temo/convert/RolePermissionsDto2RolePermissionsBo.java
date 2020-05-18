package com.creams.temo.convert;

import com.creams.temo.model.RolePermissionsBo;
import com.creams.temo.model.RolePermissionsDto;
import com.google.common.base.Converter;

public class RolePermissionsDto2RolePermissionsBo extends Converter<RolePermissionsDto,RolePermissionsBo> {


    private RolePermissionsDto2RolePermissionsBo() {
    }

    public static RolePermissionsDto2RolePermissionsBo getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final RolePermissionsDto2RolePermissionsBo INSTANCE = new RolePermissionsDto2RolePermissionsBo();
    }
    @Override
    protected RolePermissionsBo doForward(RolePermissionsDto rolePermissionsDto) {
        return RolePermissionsBo.builder()
                .id(rolePermissionsDto.getId())
                .permissionsId(rolePermissionsDto.getPermissionsId())
                .roleId(rolePermissionsDto.getRoleId()).build();
    }

    @Override
    protected RolePermissionsDto doBackward(RolePermissionsBo rolePermissionsBo) {
        return RolePermissionsDto.builder()
                .id(rolePermissionsBo.getId())
                .permissionsId(rolePermissionsBo.getPermissionsId())
                .roleId(rolePermissionsBo.getRoleId()).build();
    }
}
