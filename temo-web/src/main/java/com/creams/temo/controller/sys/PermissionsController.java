package com.creams.temo.controller.sys;

import com.creams.temo.annotation.CheckPermissions;
import com.creams.temo.biz.PermissionsService;
import com.creams.temo.convert.PermissionsAo2PermissionsBo;
import com.creams.temo.entity.result.JsonResult;
import com.creams.temo.model.PermissionsAo;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@Api("PermissionsController Api")
@RequestMapping("/permissions")
public class PermissionsController {

    @Autowired
    PermissionsService permissionsService;

    final PermissionsAo2PermissionsBo permissionsAo2PermissionsBo = PermissionsAo2PermissionsBo.getInstance();

    @ApiOperation("新增权限")
    @PostMapping(value = "/addPermissions")
    @CheckPermissions(role = "admin")
    public JsonResult addPermissions(@RequestBody PermissionsAo permissions) {
        try {
            permissionsService.addPermissions(permissionsAo2PermissionsBo.convert(permissions));
            return new JsonResult("操作成功", 200, null, true);

        } catch (Exception e) {
            return new JsonResult("操作失败", 500, e.getMessage(), false);
        }
    }

    @ApiOperation("修改权限")
    @PutMapping("/updatePermissions")
    @CheckPermissions(role = "admin")
    public JsonResult updatePermissions(@RequestBody PermissionsAo permissions) {
        try {
            permissionsService.updatePermission(permissionsAo2PermissionsBo.convert(permissions));
            return new JsonResult("操作成功", 200, null, true);
        } catch (Exception e) {
            return new JsonResult("操作失败", 500, e.getMessage(), false);
        }
    }

    @ApiOperation("查询所有权限")
    @GetMapping("/queryPermissions/{page}")
    @CheckPermissions(role = "admin")
    public JsonResult queryPermissions(@PathVariable(value = "page") Integer page) {
        try {
            PageInfo<PermissionsAo> pageInfo = new PageInfo<>(Lists.newArrayList(permissionsAo2PermissionsBo.reverse().convertAll(permissionsService.queryPermissionsByPage(page).getList())));
            HashMap<String, Object> map = new HashMap<>();
            map.put("list", pageInfo.getList());
            map.put("total", pageInfo.getTotal());
            return new JsonResult("操作成功", 200, map, true);
        } catch (Exception e) {
            return new JsonResult("操作失败", 500, e.getMessage(), false);
        }
    }

    @ApiOperation("查询该角色下的权限")
    @GetMapping("/{roleId}/info")
    @CheckPermissions(role = "admin")
    public JsonResult queryPermissionsByRoleId(@PathVariable(value = "roleId") String roleId) {
        try {
            List<PermissionsAo> permissions =Lists.newArrayList(permissionsAo2PermissionsBo.reverse().convertAll(permissionsService.queryPermissionsByRoleId(roleId)));
            return new JsonResult("操作成功", 200, permissions, true);
        } catch (Exception e) {
            return new JsonResult("操作失败", 500, e.getMessage(), false);
        }
    }

    @ApiOperation("更新权限状态")
    @PutMapping("/setPermissionsStatus/{id}")
    @CheckPermissions(role = "admin")
    public JsonResult setPermissionsStatus(@PathVariable(value = "id") String permissionsId, Integer status) {
        try {
            permissionsService.updatePermissionStatus(permissionsId, status);
            return new JsonResult("操作成功", 200, null, true);
        } catch (Exception e) {
            return new JsonResult("操作失败", 500, e.getMessage(), false);
        }
    }

    @ApiOperation("查询模块信息")
    @GetMapping("/getModule")
    @CheckPermissions(role = "admin")
    public JsonResult getPermissionsModule(){
        try {
            return new JsonResult("操作成功", 200, permissionsService.queryPermissonsModule(), true);
        } catch (Exception e) {
            return new JsonResult("操作失败", 500, e.getMessage(), false);
        }
    }

    @ApiOperation("根据模块查询权限")
    @GetMapping("/getPermissonsByModule/{moduleId}")
    @CheckPermissions(role = "admin")
    public JsonResult getPermissonsByModule(@PathVariable("moduleId") Integer moduleId ){
        try {

            return new JsonResult("操作成功", 200, permissionsService.queryPermissionsByModuleId(moduleId), true);
        } catch (Exception e) {
            return new JsonResult("操作失败", 500, e.getMessage(), false);
        }
    }
}
