package com.creams.temo.convert;

import com.creams.temo.model.PermissionsModuleBo;
import com.creams.temo.model.PermissionsModuleDto;
import com.google.common.base.Converter;

public class PermissionsModuleDto2PermissionsModuleBo extends Converter<PermissionsModuleDto,PermissionsModuleBo> {

    public PermissionsModuleDto2PermissionsModuleBo(){};


    public static PermissionsModuleDto2PermissionsModuleBo getInstance() {
        return PermissionsModuleDto2PermissionsModuleBo.SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final PermissionsModuleDto2PermissionsModuleBo INSTANCE = new PermissionsModuleDto2PermissionsModuleBo();
    }
    @Override
    protected PermissionsModuleBo doForward(PermissionsModuleDto permissionsModuleDto) {
        return PermissionsModuleBo.builder()
                .id(permissionsModuleDto.getId())
                .moduleName(permissionsModuleDto.getModuleName()).build();
    }

    @Override
    protected PermissionsModuleDto doBackward(PermissionsModuleBo permissionsModuleBo) {
        return PermissionsModuleDto.builder()
                .id(permissionsModuleBo.getId())
                .moduleName(permissionsModuleBo.getModuleName()).build();
    }

}
