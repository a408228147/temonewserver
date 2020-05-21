package com.creams.temo.convert;

import com.creams.temo.model.RoleBo;
import com.creams.temo.model.RoleDto;
import com.google.common.base.Converter;

public class RoleDto2RoleBo extends Converter<RoleDto,RoleBo> {

    private RoleDto2RoleBo() {
    }

    public static RoleDto2RoleBo getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final RoleDto2RoleBo INSTANCE = new RoleDto2RoleBo();
    }
    @Override
    protected RoleBo doForward(RoleDto roleDto) {
        if (roleDto==null){
            return null;
        }
        return RoleBo.builder().id(roleDto.getId())
                .roleId(roleDto.getRoleId())
                .status(roleDto.getStatus())
                .roleName(roleDto.getRoleName()).build();
    }

    @Override
    protected RoleDto doBackward(RoleBo roleBo) {
        if (roleBo==null){
            return null;
        }
        return RoleDto.builder().id(roleBo.getId())
                .roleId(roleBo.getRoleId())
                .status(roleBo.getStatus())
                .roleName(roleBo.getRoleName()).build();
    }
}
