package com.creams.temo.biz;

import com.creams.temo.model.UserBo;

public interface UserServer {
    UserBo  queryUserByName(String userName);
}
