package com.creams.temo.biz;

import com.creams.temo.model.PermissionsBo;
import com.creams.temo.model.RoleBo;
import com.creams.temo.model.UserBo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {
    UserBo  queryUserByName(String userName);
    List<RoleBo> queryRoleByUserId(String userId);
    List<PermissionsBo> queryPermissionsByRoleId(String roleId);

    void addUser(UserBo convert);

    void updateUser(UserBo convert);

    PageInfo<UserBo> queryUsers(Integer page);

    void updateUserStatus(String userId, Integer status);
}
