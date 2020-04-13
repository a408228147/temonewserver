package com.creams.temo.mapper;

import com.creams.temo.model.PermissionsDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PermissionsMapper {
    List<PermissionsDto> queryPermissionsByRoleId(@Param("role_id") String RoleId);
}
