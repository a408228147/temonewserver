package com.creams.temo.biz.bizImpl;

import com.creams.temo.biz.UserServer;
import com.creams.temo.convert.UserDto2UserBo;
import com.creams.temo.mapper.UserMapper;
import com.creams.temo.model.UserBo;
import com.creams.temo.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServerImpl implements UserServer {
    final UserDto2UserBo userDto2UserBo =UserDto2UserBo.getInstance();
    @Autowired
    UserMapper userMapper;
    @Override
    public UserBo queryUserByName(String userName) {
        UserDto userDto= userMapper.queryUserByName(userName);
        UserBo userBo=userDto2UserBo.convert(userDto);
        return userBo;
    }


}
