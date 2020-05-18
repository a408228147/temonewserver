package com.creams.temo.mapper;

import com.creams.temo.model.PermissionsDto;
import com.creams.temo.model.PermissionsModuleDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PermissionsMapper {
    List<PermissionsDto> queryPermissionsByRoleId(@Param("role_id") String RoleId);

    List<PermissionsDto> queryPermissions();

    void updatePermission(PermissionsDto permissionsDto);

    void updatePermissionStatus(@Param("permissions_id") String permissionsId, @Param("status") Integer status);

    void addPermissions(PermissionsDto permissionsDto);

    List<PermissionsModuleDto> queryPermissonsModule();

    List<PermissionsDto> queryPermissionsByModuleId(@Param("module_id") Integer moduleId);
}
