package com.creams.temo.convert;

import com.creams.temo.model.PermissionsAo;
import com.creams.temo.model.PermissionsBo;
import com.google.common.base.Converter;

public class PermissionsAo2PermissionsBo extends Converter<PermissionsAo,PermissionsBo> {

      public PermissionsAo2PermissionsBo(){};


    public static PermissionsAo2PermissionsBo getInstance() {
        return PermissionsAo2PermissionsBo.SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final PermissionsAo2PermissionsBo INSTANCE = new PermissionsAo2PermissionsBo();
    }
    @Override
    protected PermissionsBo doForward(PermissionsAo permissionsAo) {
        return PermissionsBo.builder().status(permissionsAo.getStatus())
                .roleId(permissionsAo.getRoleId())
                .permissionsName(permissionsAo.getPermissionsName())
                .id(permissionsAo.getId())
                .build();
    }

    @Override
    protected PermissionsAo doBackward(PermissionsBo permissionsBo) {
        return PermissionsAo.builder().status(permissionsBo.getStatus())
                .roleId(permissionsBo.getRoleId())
                .permissionsName(permissionsBo.getPermissionsName())
                .id(permissionsBo.getId())
                .build();
    }
}
