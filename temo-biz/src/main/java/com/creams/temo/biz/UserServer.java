package com.creams.temo.biz;

import com.creams.temo.model.PermissionsBo;
import com.creams.temo.model.RoleBo;
import com.creams.temo.model.UserBo;

import java.util.List;

public interface UserServer {
    UserBo  queryUserByName(String userName);
    List<RoleBo> queryRoleByUserId(String userId);
    List<PermissionsBo> queryPermissionsByRoleId(String roleId);
}
