package com.creams.temo.convert;

import com.creams.temo.model.UserBo;
import com.creams.temo.model.UserDto;
import com.google.common.base.Converter;
public class UserDto2UserBo extends Converter<UserDto,UserBo> {
    final UserDto2UserBo converter = UserDto2UserBo.getInstance();

    private UserDto2UserBo() {
    }

    public static UserDto2UserBo getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final UserDto2UserBo INSTANCE = new UserDto2UserBo();
    }
    @Override
    protected UserBo doForward(UserDto userDto) {
        if (userDto==null)
        {
            return null;
        }
        UserBo userBo=UserBo.builder()
                .id(userDto.getId())
                .userId(userDto.getUserId())
                .userName(userDto.getUserName())
                .password(userDto.getPassword())
                .status(userDto.getStatus())
                .email(userDto.getEmail())
                .build();

        return userBo;
    }

    @Override
    protected UserDto doBackward(UserBo userBo) {

        if (userBo == null) {
            return null;
        }
        return UserDto.builder()
                .id(userBo.getId())
                .userId(userBo.getUserId())
                .userName(userBo.getUserName())
                .password(userBo.getPassword())
                .status(userBo.getStatus())
                .email(userBo.getEmail())
                .build();
    }



}
