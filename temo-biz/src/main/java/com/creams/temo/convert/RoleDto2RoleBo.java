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
        return RoleBo.builder().id(roleDto.getId())
                .roleId(roleDto.getRoleId())
                .roleName(roleDto.getRoleName())
                .userId(roleDto.getUserId()).build();
    }

    @Override
    protected RoleDto doBackward(RoleBo roleBo) {
        return RoleDto.builder().id(roleBo.getId())
                .roleId(roleBo.getRoleId())
                .roleName(roleBo.getRoleName())
                .userId(roleBo.getUserId()).build();
    }
}
