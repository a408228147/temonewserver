package com.creams.temo.biz;

import com.creams.temo.model.PermissionsBo;
import com.creams.temo.model.PermissionsModuleBo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface PermissionsService {
    void addPermissions(PermissionsBo permissions);


    void updatePermission(PermissionsBo permissions);

    PageInfo<PermissionsBo> queryPermissionsByPage(Integer page);

    List<PermissionsBo> queryPermissionsByRoleId(String roleId);

    void updatePermissionStatus(String permissionsId, Integer status);

    List<PermissionsModuleBo> queryPermissonsModule();

    List<PermissionsBo> queryPermissionsByModuleId(Integer moduleId);


}
