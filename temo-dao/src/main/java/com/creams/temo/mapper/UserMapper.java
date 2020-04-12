package com.creams.temo.mapper;

import com.creams.temo.model.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    UserDto queryUserByName(@Param("user_name")String userName);
}
