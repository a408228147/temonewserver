package com.creams.temo.controller.database;

import com.creams.temo.biz.DatabaseService;
import com.creams.temo.biz.SqlExecuteService;
import com.creams.temo.convert.DatabaseAo2DatabaseBo;
import com.creams.temo.model.DatabaseAo;
import com.creams.temo.model.DatabaseBo;
import com.creams.temo.result.JsonResult;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


/**
 * 数据库配置管理控制层
 */

@RestController
@Api("DatabaseController Api")
@RequestMapping(value = "/database")
public class DatabaseController {

    @Autowired
    private DatabaseService databaseService;

    @Autowired
    private SqlExecuteService sqlExecuteService;
    final DatabaseAo2DatabaseBo databaseAo2DatabaseBo= DatabaseAo2DatabaseBo.getInstance();

    @ApiOperation("查询所有数据库信息")
    @GetMapping(value = "/")
    public JsonResult queryAllDatabase( @RequestParam(required = false) @ApiParam("100-mysql,200-redis") String dbType){
        try{
            List<DatabaseBo> databaseResponses = databaseService.queryAllDatabase(dbType);
            return new JsonResult("操作成功", 200, databaseResponses, true);
        }catch (Exception e ){
            e.printStackTrace();
            return new JsonResult("操作失败",500,null,false);
        }
    }

    @ApiOperation("模糊查询数据库列表")
    @GetMapping(value = "/{page}")
    public JsonResult queryDatabaseByName(@PathVariable(required = false) @ApiParam("页数") Integer page,
                                       @RequestParam(value = "dbName",required = false) @ApiParam("数据库名") String dbName,
                                          @RequestParam(value = "dbType",required = false) @ApiParam("数据库类型") String dbType){
        try {
            PageInfo<DatabaseBo> pageInfo = databaseService.queryDatabaseByName(page, dbName,dbType);
            HashMap<String,Object> map = new HashMap<>();
            map.put("list", pageInfo.getList());
            map.put("total", pageInfo.getTotal());
            return new JsonResult("操作成功", 200, map, true);
        }catch (Exception e){
            e.printStackTrace();
            return new JsonResult("操作失败",500,null,false);
        }

    }


    @ApiOperation("查询数据库详情")
    @GetMapping(value = "/{id}/info")
    public JsonResult queryDatabaseById(@PathVariable("id") @ApiParam("数据库id") String dbId){

        try {
            DatabaseBo databaseResponse = databaseService.queryDatabaseById(dbId);
            if (databaseResponse != null){
                return new JsonResult("操作成功",200,databaseResponse,true);
            }else {
                return new JsonResult("数据为空",200,null,true);
            }

        }catch (Exception e){
            e.printStackTrace();
            return new JsonResult("操作失败",500,null,false);
        }

    }

    @ApiOperation("新增数据库配置")
    @PostMapping(value = "/")
    public JsonResult addDatabase(@RequestBody DatabaseAo databaseRequest){
        try {
            String dbId = databaseService.addDatabase(databaseAo2DatabaseBo.convert(databaseRequest));
            return new JsonResult("操作成功", 200, dbId, true);
        }catch (Exception e){
            e.printStackTrace();
            return new JsonResult("操作失败",500,null,false);
        }


    }

    @ApiOperation("修改数据库")
    @PutMapping(value = "/{id}")
    public JsonResult updateDatabaseById(@PathVariable(value = "id") String dbId, @RequestBody DatabaseAo databaseRequest){
        try {
            databaseService.updateDatabaseById(databaseAo2DatabaseBo.convert(databaseRequest));
            return new JsonResult("操作成功", 200, null, true);
        }catch (Exception e){
            e.printStackTrace();
            return new JsonResult("操作失败",500,null,false);
        }

    }


    @ApiOperation("删除数据库")
    @DeleteMapping(value = "/delete/{id}")
    public JsonResult deleteDatabaseById(@PathVariable("id") @ApiParam("数据库id") String dbId){
        try {
            databaseService.deleteDatabaseById(dbId);
            return new JsonResult("操作成功", 200, null, true);
        }catch (Exception e){
            e.printStackTrace();
            return new JsonResult("操作失败",500,null,false);
        }

    }

    @ApiOperation("测试数据库连接")
    @PostMapping(value = "/testConnect")
    public JsonResult testConnect(@RequestBody DatabaseAo databaseRequest){
        try{
            sqlExecuteService.testConnect(databaseAo2DatabaseBo.convert(databaseRequest));
            return new JsonResult("连接成功", 200, null, true);

        }catch (Exception e){
            e.printStackTrace();
            return new JsonResult("连接失败",500,e.getMessage(),false);
        }
    }



}
