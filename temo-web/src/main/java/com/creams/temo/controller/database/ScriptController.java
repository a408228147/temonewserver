package com.creams.temo.controller.database;


import com.creams.temo.biz.ScriptService;
import com.creams.temo.convert.ScriptDbAo2ScriptDbBo;
import com.creams.temo.entity.result.JsonResult;
import com.creams.temo.model.ScriptDbAo;
import com.creams.temo.model.ScriptDbBo;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;


/**
 * 脚本管理控制层
 */

@RestController
@Api("ScriptController Api")
@RequestMapping(value = "/script")
public class ScriptController {

    @Autowired
    private ScriptService scriptService;

    final ScriptDbAo2ScriptDbBo scriptDbAo2ScriptDbBo =ScriptDbAo2ScriptDbBo.getInstance();
    @ApiOperation(value = "查询脚本列表", notes = "查询所有脚本")
    @GetMapping(value = "/list")
    public JsonResult queryAllScript(){
        return new JsonResult("操作成功",200,Lists.newArrayList(scriptDbAo2ScriptDbBo.reverse().convertAll(scriptService.queryAllScript())),true);
    }

    @ApiOperation(value = "根据脚本名称和数据库id获取脚本")
    @GetMapping(value = "/queryScriptDbByNameAndDbId/{page}")
    public JsonResult queryScriptDbByNameAndDbId(@PathVariable(value = "page")  Integer page ,
                                                 @RequestParam(value = "dbId", required = false)
                                                 @ApiParam(value = "数据库id") String dbId,
                                                 @RequestParam(value = "scriptName", required = false)
                                                 @ApiParam(value = "脚本名称") String scriptName){

        try {
            if (scriptName == null){
                scriptName = "";
            }
            if (page == null){
                page = 1;
            }

            PageInfo<ScriptDbAo> pageInfo = new PageInfo<>(Lists.newArrayList(scriptDbAo2ScriptDbBo.reverse().convertAll(scriptService.queryScriptDbByNameAndDbId(page,dbId,scriptName).getList())));
            Map<String, Object> map = new HashMap<>();
            map.put("list", pageInfo.getList());
            map.put("total", pageInfo.getTotal());
            return new JsonResult("操作成功",200,map,true);

        }catch (Exception e){
            e.printStackTrace();
            return new JsonResult("操作失败",500,null,false);
        }

    }


    @ApiOperation("查询脚本详情")
    @GetMapping(value = "/{id}/info")
    public JsonResult queryScriptById(@PathVariable("id") @ApiParam("脚本id") String scriptId){

        try {
            ScriptDbBo scriptResponse = scriptService.queryScriptById(scriptId);
            if (scriptResponse != null){
                return new JsonResult("操作成功",200,scriptDbAo2ScriptDbBo.reverse().convert(scriptResponse),true);
            }else {
                return new JsonResult("数据为空",200,null,true);
            }

        }catch (Exception e){
            e.printStackTrace();
            return new JsonResult("操作失败",500,null,false);
        }

    }

    @ApiOperation("新增脚本")
    @PostMapping(value = "/addScript")
    public JsonResult addScript(@RequestBody ScriptDbAo scriptRequest){
        try {

            String scriptId = scriptService.addScript(scriptDbAo2ScriptDbBo.convert(scriptRequest));
            return new JsonResult("操作成功", 200, scriptId, true);
        }catch (Exception e){
            e.printStackTrace();
            return new JsonResult("操作失败",500,null,false);
        }


    }

    @ApiOperation("修改脚本")
    @PutMapping(value = "updateScriptById/{id}")
    public JsonResult updateScriptById( @RequestBody ScriptDbAo scriptRequest){
        try {
            scriptService.updateScriptById(scriptDbAo2ScriptDbBo.convert(scriptRequest));
            return new JsonResult("操作成功", 200, null, true);
        }catch (Exception e){
            e.printStackTrace();
            return new JsonResult("操作失败",500,null,false);
        }

    }


    @ApiOperation("删除脚本")
    @DeleteMapping(value = "deleteScriptById/{id}")
    public JsonResult deleteScriptById(@PathVariable("id") @ApiParam("脚本id") String scriptId){
        try {
            scriptService.deleteScriptById(scriptId);
            return new JsonResult("操作成功", 200, null, true);
        }catch (Exception e){
            e.printStackTrace();
            return new JsonResult("操作失败",500,null,false);
        }

    }

}
