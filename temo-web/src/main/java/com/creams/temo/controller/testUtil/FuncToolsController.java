package com.creams.temo.controller.testUtil;

import com.alibaba.fastjson.JSON;
import com.creams.temo.annotation.CheckPermissions;
import com.creams.temo.biz.FuncToolsService;
import com.creams.temo.convert.FuncToolsAo2FuncToolsBo;
import com.creams.temo.entity.FuncToolsEntity;
import com.creams.temo.entity.UserInfo;
import com.creams.temo.entity.result.JsonResult;
import com.creams.temo.model.FuncToolsAo;
import com.creams.temo.model.FuncToolsBo;

import com.creams.temo.tools.ScriptUtils;
import com.creams.temo.tools.ShiroUtils;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Api("FuncToolsController APi")
@RequestMapping("/FuncTools")
public class FuncToolsController {

    final FuncToolsAo2FuncToolsBo funcToolsAo2FuncToolsBo = FuncToolsAo2FuncToolsBo.getInstance();
    @Autowired
    FuncToolsService funcToolsService;
    @Autowired
    ScriptUtils scriptUtils;

    @ApiOperation("保存接口")
    @PostMapping("/savaFunc")
    @CheckPermissions()
    public JsonResult saveFunc(@RequestBody FuncToolsAo funcToolsAo) {
        try {
            UserInfo user = ShiroUtils.getUserEntity();
            funcToolsAo.setCreator(user.getUserName());
            funcToolsService.saveFunc(funcToolsAo2FuncToolsBo.convert(funcToolsAo));
            return new JsonResult("保存成功", 200, "", true);
        } catch (Exception e) {
            return new JsonResult("保存失败", 500, e.getMessage(), false);
        }
    }
    @ApiOperation("更新函数")
    @PostMapping("/updateFunc")
    @CheckPermissions()
    public JsonResult updateFunc(@RequestBody FuncToolsAo funcToolsAo){
        try {
            UserInfo user = ShiroUtils.getUserEntity();
            funcToolsAo.setCreator(user.getUserName());
            funcToolsService.updateFuncById(funcToolsAo2FuncToolsBo.convert(funcToolsAo));
            return new JsonResult("更新成功", 200, "", true);
        } catch (Exception e) {
            return new JsonResult("更新失败", 500, e.getMessage(), false);
        }
    }
    @ApiOperation("查询函数接口")
    @GetMapping("/findFunc")
    @CheckPermissions()
    public JsonResult findFunc(@RequestParam("funcName") String funcName) {
        try {
            FuncToolsBo funcToolsBo = FuncToolsBo.builder().funcName(funcName).build();
            UserInfo user = ShiroUtils.getUserEntity();
            funcToolsBo.setCreator(user.getUserName());
            List<FuncToolsBo> funcToolsBos = funcToolsService.findFunc(funcToolsBo);
            return new JsonResult("success", 200, Lists.newArrayList(funcToolsAo2FuncToolsBo.reverse().convertAll(funcToolsBos)), true);
        } catch (Exception e) {
            return new JsonResult("fail", 500, e.getMessage(), false);
        }
    }
    @ApiOperation("测试接口")
    @GetMapping("/testFunc")
    @CheckPermissions()
    public JsonResult testFunc(@RequestParam("id") Integer id, @RequestParam("params") String params) {
        try {
            UserInfo user = ShiroUtils.getUserEntity();
            FuncToolsBo funcToolsBo = FuncToolsBo.builder().id(id).creator(user.getUserName()).build();
            List<FuncToolsBo> funcToolsBos = funcToolsService.findFunc(funcToolsBo);
            FuncToolsEntity funcToolsEntity = FuncToolsEntity.builder()
                    .funcLang(funcToolsBos.get(0).getFuncLang())
                    .funcName(funcToolsBos.get(0).getFuncName())
                    .funcParams(funcToolsBos.get(0).getFuncParams())
                    .funcScript(funcToolsBos.get(0).getFuncScript()).build();
            Map map = (Map) JSON.parse(params);
            return new JsonResult("调试成功", 200, scriptUtils.invokeScript(funcToolsEntity, map), true);
        } catch (Exception e) {
            return new JsonResult("调试失败", 500, e.getMessage(), false);
        }

    }

    @ApiOperation("调试接口")
    @GetMapping("/debugFunc")
    @CheckPermissions()
    public JsonResult debugFunc(@RequestBody FuncToolsAo funcToolsAo, @RequestParam("params") String params) {
        try {
            FuncToolsEntity funcToolsEntity = FuncToolsEntity.builder()
                    .funcLang(funcToolsAo.getFuncLang())
                    .funcName(funcToolsAo.getFuncName())
                    .funcParams(funcToolsAo.getFuncParams())
                    .funcScript(funcToolsAo.getFuncScript()).build();
            Map map = (Map) JSON.parse(params);
            return new JsonResult("调试成功", 200, scriptUtils.invokeScript(funcToolsEntity, map), true);
        } catch (Exception e) {
            return new JsonResult("调试失败", 500, e.getMessage(), false);
        }

    }

    @ApiOperation("删除函数")
    @GetMapping("/deleteFunc")
    @CheckPermissions()
    public JsonResult deletefunc(@RequestParam("id") Integer id){
        try {
            funcToolsService.deleteFuncById(id);
            return new JsonResult("删除成功", 200, "", true);
        } catch (Exception e) {
            return new JsonResult("删除失败", 500, e.getMessage(), false);
        }
    }
}

