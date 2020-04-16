package com.creams.temo.biz.bizImpl;

import com.creams.temo.biz.RoleService;
import com.creams.temo.convert.RoleDto2RoleBo;

import com.creams.temo.mapper.RoleMapper;
import com.creams.temo.model.RoleBo;
import com.creams.temo.model.RoleDto;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;
    final RoleDto2RoleBo roleDto2RoleBo = RoleDto2RoleBo.getInstance();
    /**
     * 分页查询所有角色
     *
     * @return
     */
    public PageInfo<RoleBo> queryRole(Integer page) {
        PageHelper.startPage(page, 10);
        List<RoleDto> roles = roleMapper.queryRoles();
        return new PageInfo<>(Lists.newArrayList(roleDto2RoleBo.convertAll(roles)));
    }

    /**
     * 根据用户id查询角色
     *
     * @param userId
     * @return
     */
    public List<RoleBo> queryRoleByUserId(String userId) {
        return Lists.newArrayList(roleDto2RoleBo.convertAll(roleMapper.queryRolesByUserId(userId)));
    }

    /**
     * 添加角色
     *
     * @param role
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public void addRole(RoleBo role) {
        roleMapper.addRole(roleDto2RoleBo.reverse().convert(role));
    }

    /**
     * 设置角色状态
     *
     * @param roleId
     * @param status
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateRoleStatus(String roleId, Integer status) {
        roleMapper.updateRoleStatus(roleId, status);
    }


    /**
     * 修改角色
     *
     * @param role
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateRole(RoleBo role) {
        roleMapper.updateRole(roleDto2RoleBo.reverse().convert(role));
    }
}
