package com.creams.temo.biz;

import com.creams.temo.PermissionsBo;
import com.creams.temo.RoleBo;
import com.creams.temo.UserBo;

import java.util.List;

public interface UserServer {
    UserBo  queryUserByName(String userName);
    List<RoleBo> queryRoleByUserId(String userId);
    List<PermissionsBo> queryPermissionsByRoleId(String roleId);
}
