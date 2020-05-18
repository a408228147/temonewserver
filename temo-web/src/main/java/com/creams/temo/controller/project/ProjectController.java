package com.creams.temo.controller.project;

import com.creams.temo.annotation.CheckPermissions;
import com.creams.temo.entity.result.JsonResult;
import com.creams.temo.model.ProjectBo;
import com.creams.temo.biz.ProjectService;
import com.creams.temo.convert.ProjectAo2ProjectBo;
import com.creams.temo.model.ProjectAo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


/**
 * 项目控制层
 */

@RestController
@Api("ProjectController Api")
@RequestMapping("/project")
public class ProjectController {


    private static Logger logger = LoggerFactory.getLogger("fileInfoLog");

    final ProjectAo2ProjectBo projectAo2ProjectBo=ProjectAo2ProjectBo.getInstance();
    @Autowired
    ProjectService projectService;

    @ApiOperation("模糊查询项目列表")
    @GetMapping("/queryProject/{page}")
    @CheckPermissions()
    public JsonResult queryProject(@PathVariable(value = "page")  Integer page, @RequestParam(value = "filter",required = false)@ApiParam(value = "查询条件") String filter){
        try{
            if (filter == null){
                filter = "";
            }
            PageInfo<ProjectBo> pageInfo = projectService.queryByName(page,filter);
            Map<String,Object> map = new HashMap<>();
            map.put("list",pageInfo.getList());
            map.put("total",pageInfo.getTotal());
            logger.info("分页数据：\n"+map);
            return new JsonResult("操作成功",200,map,true);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new JsonResult("操作失败",500,null,false);
        }

    }

    @ApiOperation("查询项目列表")
    @GetMapping("/list")
    @CheckPermissions()
    public JsonResult queryDetail(){
        try{
            return new JsonResult("操作成功",200, projectService.queryAllProjects(),true);
        }catch (Exception e){
            return new JsonResult("系统错误",500,null,false);
        }
    }

    @ApiOperation("根据项目Id查询所属环境")
    @GetMapping("/env")
    @CheckPermissions()
    public JsonResult queryEnvByProjectId(@RequestParam String projectId){
        try{
            return new JsonResult("操作成功",200, projectService.queryEnvByProjectId(projectId),true);
        }catch (Exception e){
            return new JsonResult("系统错误",500,null,false);
        }
    }

    @ApiOperation("查询项目详情")
    @GetMapping("/{projectId}/info")
    @CheckPermissions()
    public JsonResult queryDetail(@PathVariable @ApiParam("项目id")String projectId){
        try{
            ProjectBo projectResponse = projectService.queryDetailById(projectId);
            if (projectResponse != null){
                return new JsonResult("操作成功",200,projectResponse,true);
            }else {
                return new JsonResult("操作失败",404,null,false);
            }
        }catch (Exception e){
            return new JsonResult("系统错误",500,null,false);
        }
    }

    @ApiOperation("创建项目")
    @PostMapping("/addProject")
    @CheckPermissions()
    public JsonResult addProject(@RequestBody(required = false) ProjectAo project){
        logger.info("接受到参数："+project);
        if (project==null){
            return new JsonResult("参数为空",400,null,false);
        }
        if (project.getPname() == null || "".equals(project.getPname())){
            return new JsonResult("项目名称不能为空！",400,null,false);
        }
        try{
            ProjectBo projectBo =projectAo2ProjectBo.convert(project);
            String projectId = projectService.addProject(projectBo);
            return new JsonResult("操作成功",200,projectId,true);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new JsonResult("操作失败",500,null,false);
        }

    }

    @ApiOperation("编辑项目")
    @PutMapping("/updateProject/{projectId}")
    @CheckPermissions()
    public JsonResult updateProject(@PathVariable @ApiParam("项目id") String projectId, @RequestBody(required = false) @ApiParam("项目") ProjectAo project){
        logger.info("接受到参数："+project);
        project.setPid(projectId);
        if (project == null){
            return new JsonResult("参数为空",400,null,false);
        }
        if (project.getPname() == null || "".equals(project.getPname())){
            return new JsonResult("项目名称不能为空！",400,null,false);
        }
        try{
            ProjectBo projectBo=projectAo2ProjectBo.convert(project);
            projectService.updateProjectById(projectId,projectBo);
            return new JsonResult("操作成功",200,null,true);
        }catch (Exception e){
            e.printStackTrace();
            return new JsonResult("系统错误",500,e,true);
        }
    }

    @ApiOperation("删除项目")
    @DeleteMapping("/delProject/{projectId}")
    @CheckPermissions()
    public JsonResult delProject(@PathVariable @ApiParam("项目id") String projectId){
        try{
            Integer i = projectService.delProjectById(projectId);
            if(i>0){
                return new JsonResult("操作成功",200,null,true);
            }else{
                return new JsonResult("该项目已不存在",404,null,false);
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            return new JsonResult("系统错误",500,null,false);
        }
    }
}
