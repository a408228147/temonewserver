package com.creams.temo.mapper;

import com.creams.temo.model.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    UserDto queryUserByName(@Param("user_name")String userName);

    List<UserDto> queryUsers();

    void updateUser(UserDto userDto);

    void updateUserStatus(@Param("user_id") String userId, @Param("status") Integer status);

    void addUser(UserDto user);
}
