package com.creams.temo.convert;

import com.creams.temo.model.RolePermissionsAo;
import com.creams.temo.model.RolePermissionsBo;
import com.google.common.base.Converter;

public class RolePermissionsAo2RolePermissionsBo extends Converter<RolePermissionsAo,RolePermissionsBo> {


    private RolePermissionsAo2RolePermissionsBo() {
    }

    public static RolePermissionsAo2RolePermissionsBo getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final RolePermissionsAo2RolePermissionsBo INSTANCE = new RolePermissionsAo2RolePermissionsBo();
    }
    @Override
    protected RolePermissionsBo doForward(RolePermissionsAo rolePermissionsAo) {
        return RolePermissionsBo.builder()
                .id(rolePermissionsAo.getId())
                .permissionsId(rolePermissionsAo.getPermissionsId())
                .roleId(rolePermissionsAo.getRoleId()).build();
    }

    @Override
    protected RolePermissionsAo doBackward(RolePermissionsBo rolePermissionsBo) {
        return RolePermissionsAo.builder()
                .id(rolePermissionsBo.getId())
                .permissionsId(rolePermissionsBo.getPermissionsId())
                .roleId(rolePermissionsBo.getRoleId()).build();
    }
}
