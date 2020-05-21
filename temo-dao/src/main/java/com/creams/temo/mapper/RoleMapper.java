package com.creams.temo.mapper;

import com.creams.temo.model.RoleDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper {
    List<RoleDto> queryRoles();

    void addRole(RoleDto roleDto);

    void updateRoleStatus(@Param("role_id") String roleId, @Param("status") Integer status);

    void updateRole(RoleDto roleDto);

    void bindPermissions(@Param("role_id") String roleId,@Param("permissions_id") List<String> permission_id);

    void removeAllPermissionsByRole(@Param("role_id") String roleId);
}
