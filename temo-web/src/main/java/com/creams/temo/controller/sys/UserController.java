package com.creams.temo.controller.sys;


import com.creams.temo.biz.UserService;
import com.creams.temo.convert.UserAo2UserBo;
import com.creams.temo.entity.result.JsonResult;
import com.creams.temo.model.UserAo;
import com.creams.temo.model.UserBo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@Api("UserController Api")
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    final UserAo2UserBo userAo2UserBo = UserAo2UserBo.getInstance();

    @ApiOperation("新增用户")
    @PostMapping(value = "/addUser")
    public JsonResult addUser(@RequestBody UserAo user) {
        try {
            userService.addUser(userAo2UserBo.reverse().convert(user));
            return new JsonResult("操作成功", 200, null, true);

        } catch (Exception e) {
            return new JsonResult("操作失败", 500, null, false);
        }
    }

    @ApiOperation("修改用户")
    @PutMapping(value = "/update/{id}")
    public JsonResult updateUser(@RequestBody UserAo user) {
        try {
            userService.updateUser(userAo2UserBo.reverse().convert(user));
            return new JsonResult("操作成功", 200, null, true);
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult("操作失败", 500, null, false);
        }
    }

    @ApiOperation("查询所有用户")
    @GetMapping(value = "queryUsers/{page}")
    public JsonResult queryUsers(@PathVariable(value = "page") Integer page) {
        try {
            PageInfo<UserBo> pageInfo = userService.queryUsers(page);
            HashMap<String, Object> map = new HashMap<>();
            map.put("list", pageInfo.getList());
            map.put("total", pageInfo.getTotal());
            return new JsonResult("操作成功", 200, map, true);
        } catch (Exception e) {
            return new JsonResult("操作失败", 500, null, false);
        }
    }


    @ApiOperation("更新用户状态")
    @PutMapping(value = "setUserStatus/{id}")
    public JsonResult setUserStatus(@PathVariable(value = "id") @ApiParam("用户id") String userId, Integer status) {
        try {
            userService.updateUserStatus(userId, status);
            return new JsonResult("操作成功", 200, null, true);
        } catch (Exception e) {
            return new JsonResult("操作失败", 500, null, false);
        }
    }

}
