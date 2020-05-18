package com.creams.temo.biz;

import com.creams.temo.entity.UserRoleEntity;
import com.creams.temo.model.UserRoleBo;

import java.util.List;

public interface UserRoleService {

    List<UserRoleEntity> queryRoleByUserId(String userId);

    void addUserRole(UserRoleBo userRoleBo);

    void removeUserRole(UserRoleBo userRoleBo);
}
