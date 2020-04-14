package com.creams.temo.convert;

import com.creams.temo.model.UserAo;
import com.creams.temo.UserBo;
import com.google.common.base.Converter;

public class UserBo2UserAo extends Converter<UserBo,UserAo> {

    private UserBo2UserAo() {
    }

    public static UserBo2UserAo getInstance() {
        return UserBo2UserAo.SingletonHolder.INSTANCE;
    }
    private static class SingletonHolder {
        private static final UserBo2UserAo INSTANCE = new UserBo2UserAo();
    }
    @Override
    protected UserAo doForward(UserBo userBo) {

        UserAo userAo=UserAo.builder().userId(userBo.getUserId())
                .userName(userBo.getUserName())
                .password(userBo.getPassword())
                .status(userBo.getStatus())
                .id(userBo.getId()).build();
        return userAo;
    }

    @Override
    protected UserBo doBackward(UserAo userAo) {
        UserBo userBo=UserBo.builder().userId(userAo.getUserId())
                .userName(userAo.getUserName())
                .password(userAo.getPassword())
                .status(userAo.getStatus())
                .id(userAo.getId()).build();
        return userBo;
    }

}
