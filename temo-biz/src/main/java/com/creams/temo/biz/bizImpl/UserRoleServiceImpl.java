package com.creams.temo.biz.bizImpl;

import com.creams.temo.biz.UserRoleService;
import com.creams.temo.convert.UserRoleDto2UserRoleBo;
import com.creams.temo.entity.UserRoleEntity;
import com.creams.temo.mapper.UserRoleMapper;
import com.creams.temo.model.UserRoleBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    UserRoleMapper userRoleMapper;
    final UserRoleDto2UserRoleBo userRoleDto2UserRoleBo =UserRoleDto2UserRoleBo.getInstance();
    @Override
    public List<UserRoleEntity> queryRoleByUserId(String userId) {
        return userRoleMapper.queryUserRoleByUserId(userId);
    }

    @Override
    @Transactional
    public void addUserRole(UserRoleBo userRoleBo) {
        userRoleMapper.addUserRole(userRoleDto2UserRoleBo.reverse().convert(userRoleBo));
    }

    @Override
    @Transactional
    public void removeUserRole(UserRoleBo userRoleBo) {
        userRoleMapper.removeUserRole(userRoleDto2UserRoleBo.reverse().convert(userRoleBo));

    }
}
