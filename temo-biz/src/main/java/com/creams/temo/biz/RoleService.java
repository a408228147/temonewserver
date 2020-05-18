package com.creams.temo.biz;

import com.creams.temo.model.RoleBo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface RoleService {
    void addRole(RoleBo role);

    void updateRole(RoleBo role);

    PageInfo<RoleBo> queryRole(Integer page);

    void bindPermissions(String roleId,List<String> permissionsId);

}
