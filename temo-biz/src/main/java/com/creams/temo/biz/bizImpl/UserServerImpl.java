package com.creams.temo.biz.bizImpl;

import com.creams.temo.*;
import com.creams.temo.biz.UserServer;
import com.creams.temo.convert.PermissionsDto2PermissionsBo;
import com.creams.temo.convert.RoleDto2RoleBo;
import com.creams.temo.convert.UserDto2UserBo;
import com.creams.temo.mapper.PermissionsMapper;
import com.creams.temo.mapper.RoleMapper;
import com.creams.temo.mapper.UserMapper;
import com.creams.temo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServerImpl implements UserServer {
    final UserDto2UserBo userDto2UserBo = UserDto2UserBo.getInstance();
    final PermissionsDto2PermissionsBo permissionsDto2PermissionsBo = PermissionsDto2PermissionsBo.getInstance();
    final RoleDto2RoleBo roleDto2RoleBo = RoleDto2RoleBo.getInstance();
    @Autowired
    UserMapper userMapper;
    @Autowired
    PermissionsMapper permissionsMapper;
    @Autowired
    RoleMapper roleMapper;

    @Override
    public UserBo queryUserByName(String userName) {
        UserDto userDto = userMapper.queryUserByName(userName);
        UserBo userBo = userDto2UserBo.convert(userDto);
        return userBo;
    }

    @Override
    public List<RoleBo> queryRoleByUserId(String userId) {
        List<RoleDto> roleDtoList = roleMapper.queryRolesByUserId(userId);
        List<RoleBo> roleBoList = (List<RoleBo>) roleDto2RoleBo.convertAll(roleDtoList);
        return roleBoList;
    }

    @Override
    public List<PermissionsBo> queryPermissionsByRoleId(String roleId) {
        List<PermissionsDto> permissionsDtoList = permissionsMapper.queryPermissionsByRoleId(roleId);

        return (List<PermissionsBo>) permissionsDto2PermissionsBo.convertAll(permissionsDtoList);
    }


}
