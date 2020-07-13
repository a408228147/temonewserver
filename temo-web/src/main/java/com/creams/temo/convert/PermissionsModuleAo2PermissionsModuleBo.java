package com.creams.temo.convert;

import com.creams.temo.model.PermissionsModuleAo;
import com.creams.temo.model.PermissionsModuleBo;
import com.google.common.base.Converter;

public class PermissionsModuleAo2PermissionsModuleBo extends Converter<PermissionsModuleAo,PermissionsModuleBo> {

    private PermissionsModuleAo2PermissionsModuleBo(){};


    public static PermissionsModuleAo2PermissionsModuleBo getInstance() {
        return PermissionsModuleAo2PermissionsModuleBo.SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final PermissionsModuleAo2PermissionsModuleBo INSTANCE = new PermissionsModuleAo2PermissionsModuleBo();
    }
    @Override
    protected PermissionsModuleBo doForward(PermissionsModuleAo permissionsModuleAo) {
        return PermissionsModuleBo.builder()
                .id(permissionsModuleAo.getId())
                .moduleName(permissionsModuleAo.getModuleName()).build();
    }

    @Override
    protected PermissionsModuleAo doBackward(PermissionsModuleBo permissionsModuleBo) {
        return PermissionsModuleAo.builder()
                .id(permissionsModuleBo.getId())
                .moduleName(permissionsModuleBo.getModuleName()).build();
    }

}
