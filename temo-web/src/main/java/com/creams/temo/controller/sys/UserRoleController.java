package com.creams.temo.controller.sys;

import com.creams.temo.biz.UserRoleService;
import com.creams.temo.convert.UserRoleAo2UserRoleBo;
import com.creams.temo.entity.UserRoleEntity;
import com.creams.temo.entity.result.JsonResult;
import com.creams.temo.model.UserRoleAo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api("UserRoleController Api")
@RequestMapping("/userrole")
public class UserRoleController {
    @Autowired
    UserRoleService userRoleService;

    final UserRoleAo2UserRoleBo userRoleAo2UserRoleBo = UserRoleAo2UserRoleBo.getInstance();
    @ApiOperation("查询该用户下的角色")
    @GetMapping("/{userId}/info")
    public JsonResult queryRolesByUserId(@PathVariable("userId") String userId) {
        try {
            List<UserRoleEntity> roles = userRoleService.queryRoleByUserId(userId);
            return new JsonResult("操作成功", 200, roles, true);
        } catch (Exception e) {
            return new JsonResult("操作失败", 500, e.getMessage(), false);
        }
    }

    @ApiOperation("用户绑定角色")
    @PostMapping("/bindRole")
    public JsonResult bindRole(@RequestBody UserRoleAo userRoleAo){
        try {
            userRoleService.addUserRole(userRoleAo2UserRoleBo.convert(userRoleAo));
            return new JsonResult("操作成功", 200, "sccuess", true);
        } catch (Exception e) {
            return new JsonResult("操作失败", 500, e.getMessage(), false);
        }
    }

    @ApiOperation("用户解除绑定")
    @PostMapping("/removeUserRole")
    public JsonResult removeUserRole(@RequestBody UserRoleAo userRoleAo){
        try {
            userRoleService.removeUserRole(userRoleAo2UserRoleBo.convert(userRoleAo));
            return new JsonResult("操作成功", 200, "sccuess", true);
        } catch (Exception e) {
            return new JsonResult("操作失败", 500, e.getMessage(), false);
        }
    }
}
