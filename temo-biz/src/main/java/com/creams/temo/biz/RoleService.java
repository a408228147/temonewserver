package com.creams.temo.biz;

import com.creams.temo.model.RoleBo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface RoleService {
    void addRole(RoleBo role);

    void updateRole(RoleBo role);

    PageInfo<RoleBo> queryRole(Integer page);

    List<RoleBo> queryRoleByUserId(String userId);

    void updateRoleStatus(String roleId, Integer status);
}
