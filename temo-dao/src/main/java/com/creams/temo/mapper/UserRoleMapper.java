package com.creams.temo.mapper;

import com.creams.temo.entity.UserRoleEntity;
import com.creams.temo.model.UserRoleDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserRoleMapper {

    List<UserRoleEntity> queryUserRoleByUserId(@Param("user_id") String userId);
    void addUserRole(UserRoleDto userRoleDto);
    void removeUserRole(UserRoleDto userRoleDto);
}
