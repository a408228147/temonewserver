package com.creams.temo.convert;

import com.creams.temo.model.UserAo;
import com.creams.temo.model.UserBo;
import com.google.common.base.Converter;

public class UserAo2UserBo extends Converter<UserBo,UserAo> {

    private UserAo2UserBo() {
    }

    public static UserAo2UserBo getInstance() {
        return UserAo2UserBo.SingletonHolder.INSTANCE;
    }
    private static class SingletonHolder {
        private static final UserAo2UserBo INSTANCE = new UserAo2UserBo();
    }
    @Override
    protected UserAo doForward(UserBo userBo) {
     if (userBo==null){
         return null;
     }
        UserAo userAo=UserAo.builder()
                .userId(userBo.getUserId())
                .userName(userBo.getUserName())
                .password(userBo.getPassword())
                .status(userBo.getStatus())
                .id(userBo.getId())
                .email(userBo.getEmail())
                .build();
        return userAo;
    }

    @Override
    protected UserBo doBackward(UserAo userAo) {
        if(userAo==null){
            return null;
        }
        UserBo userBo=UserBo.builder()
                .userId(userAo.getUserId())
                .userName(userAo.getUserName())
                .password(userAo.getPassword())
                .status(userAo.getStatus())
                .id(userAo.getId())
                .email(userAo.getEmail())
                .build();
        return userBo;
    }

}
