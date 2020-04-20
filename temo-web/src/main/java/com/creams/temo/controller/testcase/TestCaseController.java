package com.creams.temo.controller.testcase;

import com.creams.temo.biz.TestCaseService;
import com.creams.temo.entity.TestCase;
import com.creams.temo.result.JsonResult;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Api("TestCaseController APi")
@RequestMapping("/testcase")
public class TestCaseController {


    @Autowired
    TestCaseService testCaseService;


    @ApiOperation(value = "查询用例")
    @GetMapping(value = "/{page}")
    public JsonResult queryTestCase(@PathVariable(value = "page") Integer page,
                                    @RequestParam(value = "case_id", required = false)
                                    @ApiParam(value = "用例id") String caseId,
                                    @RequestParam(value = "evn_id", required = false)
                                    @ApiParam(value = "环境id")String envId,
                                    @RequestParam(value = "set_id", required = false)
                                    @ApiParam(value = "集合id") String setId,
                                    @RequestParam(value = "case_desc", required = false)
                                    @ApiParam(value = "用例描述") String caseDesc,
                                    @RequestParam(value = "db_id", required = false)
                                    @ApiParam(value = "数据库id") String dbId,
                                    @RequestParam(value = "case_type", required = false)
                                    @ApiParam(value = "用例类型") String caseType){
        try {
            PageInfo<TestCase> pageInfo = testCaseService.queryTestCase(page, caseId, envId, setId, caseDesc, dbId, caseType);
            Map<String, Object> map = new HashMap<>();
            map.put("list", pageInfo.getList());
            map.put("total", pageInfo.getTotal());
            return new JsonResult("操作成功", 200, map, true);
        }catch (Exception e){
            e.printStackTrace();
            return new JsonResult("操作失败", 500, null, false);
        }

    }

    @ApiOperation(value = "根据case_id查询用例信息")
    @GetMapping(value = "/{id}/info")
    public JsonResult queryTestCaseInfo(@PathVariable("id") String id){
        try{
            TestCase testCaseResponse = testCaseService.queryTestCaseInfo(id);
            return new JsonResult("操作成功", 200, testCaseResponse, true);
        }catch (Exception e){
            e.printStackTrace();
            return new JsonResult("操作失败", 500, null, false);
        }

    }

    @ApiOperation(value = "统计个人用例")
    @GetMapping(value = "statisticsTestCase/{userId}")
    public JsonResult statisticsTestCaseByUserId(@PathVariable("userId") String userId){
        try{
            return new JsonResult("操作成功", 200, testCaseService.statisticsTestCaseByUserId(userId), true);
        }catch (Exception e){
            e.printStackTrace();
            return new JsonResult("操作失败", 500, null, false);
        }
    }

    @ApiOperation(value = "新增用例")
    @PostMapping(value = "/")
    public JsonResult addTestCase(@RequestBody TestCase testCaseRequest){
        try {
            String caseId = testCaseService.addTestCase(testCaseRequest);
            return new JsonResult("操作成功", 200, caseId, true);
        }catch (Exception e){
            e.printStackTrace();
            return new JsonResult("操作失败", 500, null, false);
        }
    }


    @ApiOperation(value = "修改用例")
    @PutMapping("/{id}")
    public JsonResult updateTestCase(@RequestBody TestCase testCaseRequest){
        try {
            String caseId = testCaseRequest.getCaseId();
            testCaseService.updateTestCase(testCaseRequest);
            return new JsonResult("操作成功", 200, caseId, true);
        }catch (Exception e){
            e.printStackTrace();
            return new JsonResult("操作失败", 500, null, false);
        }
    }

    @ApiOperation(value = "复制用例")
    @PostMapping("/copyTestCase/{caseId}")
    public JsonResult copyTestCaseSet(@PathVariable String caseId){
        try {
            if (testCaseService.copyTestCase(caseId)){
                return new JsonResult("操作成功", 200, caseId, true);
            }else {
                return new JsonResult("用例集不存在", 500, caseId, false);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new JsonResult("操作失败", 500, null, false);
        }
    }

    @ApiOperation(value = "更新用例是否运行")
    @PutMapping("/{caseId}/isRun/{isRun}")
    public JsonResult updateIsRun(@PathVariable String caseId,@PathVariable Integer isRun){
        testCaseService.updateIsRun(caseId,isRun);
        return new JsonResult("操作成功", 200, null, true);
    }

    @ApiOperation(value = "根据用例id修改执行顺序")
    @PutMapping("/{caseId}/order")
    public JsonResult updateTestCaseOrder(@PathVariable @ApiParam(value = "用例id", required = true) String caseId,
                                          @RequestParam @ApiParam(value = "up上移/down下移", required = true) String move){
        try {
            Boolean result = testCaseService.updateTestCaseOrderById(caseId, move);
            if (move.equals("up") && result == false){
                return new JsonResult("无法上移，请重试", 500, null, true);
            }else if (move.equals("down") && result == false){
                return new JsonResult("无法下移，请重试", 500, null, true);
            }
            return new JsonResult("操作成功", 200, caseId, true);
        }catch (Exception e){
            e.printStackTrace();
            return new JsonResult("操作失败", 500, e, false);
        }
    }


    @ApiOperation(value = "删除用例")
    @DeleteMapping("/{id}")
    public JsonResult deleteTestCase(@PathVariable("id") @ApiParam("用例id") String caseId){
        try {
            TestCase testCaseResponse = testCaseService.queryTestCaseById(caseId);
            if (testCaseResponse == null){
                return new JsonResult("数据为空", 404, caseId, true);
            }else {
                testCaseService.deleteTestCase(caseId);
                return new JsonResult("操作成功", 200, caseId, true);
            }

        }catch (Exception e){
            e.printStackTrace();
            return new JsonResult("操作失败", 500, null, false);
        }
    }


}
