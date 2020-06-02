package com.creams.temo.biz.bizImpl;

import com.creams.temo.biz.UserService;
import com.creams.temo.convert.PermissionsDto2PermissionsBo;
import com.creams.temo.convert.RoleDto2RoleBo;
import com.creams.temo.convert.UserDto2UserBo;
import com.creams.temo.mapper.PermissionsMapper;
import com.creams.temo.mapper.RoleMapper;
import com.creams.temo.mapper.UserMapper;
import com.creams.temo.model.*;
import com.creams.temo.tools.ShiroUtils;
import com.creams.temo.tools.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
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
    public List<PermissionsBo> queryPermissionsByRoleId(String roleId) {
        List<PermissionsDto> permissionsDtoList = permissionsMapper.queryPermissionsByRoleId(roleId);

        return Lists.newArrayList(permissionsDto2PermissionsBo.convertAll(permissionsDtoList));
    }

    /**
     * 添加用户
     * @param user
     */
    @Transactional(rollbackFor = Exception.class)
    public void addUser(UserBo user){
            String userId = StringUtil.uuid();
            user.setUserId(userId);
            String shaPwd = ShiroUtils.sha256(user.getPassword(), userId);
            user.setPassword(shaPwd);
            userMapper.addUser(userDto2UserBo.reverse().convert(user));

    }

    /**
     * 设置用户状态
     *
     * @param userId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserStatus(String userId, Integer status) {
        userMapper.updateUserStatus(userId, status);
    }

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(UserBo user) {
        String shaPwd = ShiroUtils.sha256(user.getPassword(), user.getUserId());
        user.setPassword(shaPwd);
        userMapper.updateUser(userDto2UserBo.reverse().convert(user));

    }

    @Override
    public PageInfo<UserBo> queryUsers(Integer page) {
        PageHelper.startPage(page, 10);
        List<UserDto> users = userMapper.queryUsers();
        return new PageInfo<>(Lists.newArrayList(userDto2UserBo.convertAll(users)));
    }
}
