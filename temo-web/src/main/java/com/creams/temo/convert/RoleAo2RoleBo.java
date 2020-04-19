package com.creams.temo.convert;

import com.creams.temo.model.RoleAo;
import com.creams.temo.model.RoleBo;
import com.creams.temo.model.RoleDto;
import com.google.common.base.Converter;

public class RoleAo2RoleBo extends Converter<RoleAo,RoleBo> {

    private RoleAo2RoleBo() {
    }

    public static RoleAo2RoleBo getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final RoleAo2RoleBo INSTANCE = new RoleAo2RoleBo();
    }
    @Override
    protected RoleBo doForward(RoleAo roleAo) {
        if (roleAo==null){
            return null;
        }
        return RoleBo.builder().id(roleAo.getId())
                .roleId(roleAo.getRoleId())
                .roleName(roleAo.getRoleName())
                .userId(roleAo.getUserId()).build();
    }

    @Override
    protected RoleAo doBackward(RoleBo roleBo) {
        if (roleBo==null){
            return null;
        }
        return RoleAo.builder().id(roleBo.getId())
                .roleId(roleBo.getRoleId())
                .roleName(roleBo.getRoleName())
                .userId(roleBo.getUserId()).build();
    }
}
