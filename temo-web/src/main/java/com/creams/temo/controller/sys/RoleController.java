package com.creams.temo.controller.sys;

import com.creams.temo.annotation.CheckPermissions;
import com.creams.temo.biz.RoleService;

import com.creams.temo.convert.RoleAo2RoleBo;
import com.creams.temo.entity.result.JsonResult;
import com.creams.temo.model.RoleAo;
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
    @PostMapping(value = "/addRole")
    @CheckPermissions(role = "admin")
    public JsonResult addRole(@RequestBody RoleAo role) {
        try {
            roleService.addRole(roleAo2RoleBo.convert(role));
            return new JsonResult("操作成功", 200, null, true);
        } catch (Exception e) {
            return new JsonResult("操作失败", 500, null, false);
        }
    }

    @ApiOperation("修改角色")
    @GetMapping("/updateRole")
    @CheckPermissions(role = "admin")
    public JsonResult updateRole(@RequestBody RoleAo role) {
        try {
            roleService.updateRole(roleAo2RoleBo.convert(role));
            return new JsonResult("操作成功", 200, null, true);
        } catch (Exception e) {
            return new JsonResult("操作失败", 500, null, false);
        }
    }

    @ApiOperation("查询所有角色")
    @GetMapping("/queryRoles/{page}")
    @CheckPermissions(role = "admin")
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


    @ApiOperation("角色绑定权限")
    @PostMapping("/bindPermissions")
    @CheckPermissions(role = "admin")
    public JsonResult bindPermissions(@RequestParam String roleId, List<String> permissionsId){
        try {
            roleService.bindPermissions(roleId,permissionsId);
            return new JsonResult("操作成功", 200, null, true);
        } catch (Exception e) {
            return new JsonResult("操作失败", 500, null, false);
        }
    }

}
