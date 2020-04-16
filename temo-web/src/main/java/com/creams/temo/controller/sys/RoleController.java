package com.creams.temo.controller.sys;

import com.baomidou.mybatisplus.extension.api.R;
import com.creams.temo.biz.RoleService;

import com.creams.temo.convert.RoleAo2RoleBo;
import com.creams.temo.model.RoleAo;
import com.creams.temo.model.RoleBo;
import com.creams.temo.result.JsonResult;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@Api("RoleController Api")
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    final RoleAo2RoleBo roleAo2RoleBo = RoleAo2RoleBo.getInstance();

    @ApiOperation("新增角色")
    @PostMapping(value = "/")
    public JsonResult addRole(@RequestBody RoleAo role) {
        try {
            roleService.addRole(roleAo2RoleBo.convert(role));
            return new JsonResult("操作成功", 200, null, true);
        } catch (Exception e) {
            return new JsonResult("操作失败", 500, null, false);
        }
    }

    @ApiOperation("修改角色")
    @PutMapping()
    public JsonResult updateRole(@RequestBody RoleAo role) {
        try {
            roleService.updateRole(roleAo2RoleBo.convert(role));
            return new JsonResult("操作成功", 200, null, true);
        } catch (Exception e) {
            return new JsonResult("操作失败", 500, null, false);
        }
    }

    @ApiOperation("查询所有角色")
    @GetMapping("/{page}")
    public JsonResult queryRoles(@PathVariable(value = "page") Integer page) {
        try {
            PageInfo<RoleAo> pageInfo = new PageInfo<>(Lists.newArrayList(roleAo2RoleBo.reverse().convertAll(roleService.queryRole(page).getList())));
            HashMap<String, Object> map = new HashMap<>();
            map.put("list", pageInfo.getList());
            map.put("total", pageInfo.getTotal());
            return new JsonResult("操作成功", 200, map, true);
        } catch (Exception e) {
            return new JsonResult("操作失败", 500, null, false);
        }
    }

    @ApiOperation("查询该用户下的角色")
    @GetMapping("/{userId}/info")
    public JsonResult queryRolesByUserId(@PathVariable("userId") String userId) {
        try {
            List<RoleBo> roles = roleService.queryRoleByUserId(userId);
            return new JsonResult("操作成功", 200, Lists.newArrayList(roleAo2RoleBo.reverse().convertAll(roles)), true);
        } catch (Exception e) {
            return new JsonResult("操作失败", 500, null, false);
        }
    }

    @ApiOperation("更新角色状态")
    @PutMapping(value = "/{id}")
    public JsonResult setRoleStatus(@PathVariable(value = "id") @ApiParam("角色id") String roleId, Integer status) {
        try {
            roleService.updateRoleStatus(roleId, status);
            return new JsonResult("操作成功", 200, null, true);

        } catch (Exception e) {
            return new JsonResult("操作失败", 500, null, false);
        }
    }
}
