package com.creams.temo.controller.database;

import com.creams.temo.biz.SqlExecuteService;
import com.creams.temo.convert.ScriptDbAo2ScriptDbBo;
import com.creams.temo.model.ScriptDbAo;
import com.creams.temo.result.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Api("SqlExecuteController Api")
@RequestMapping(value = "/sqlExecute")
public class SqlExecuteController {

    @Autowired
    private SqlExecuteService sqlExecuteService;

    final ScriptDbAo2ScriptDbBo scriptDbAo2ScriptDbBo = ScriptDbAo2ScriptDbBo.getInstance();
    /**
     *
     * @param scriptRequest
     * @return
     */
    @ApiOperation("执行数据库脚本")
    @PostMapping(value = "/")
    public JsonResult sqlExecute(@RequestBody ScriptDbAo scriptRequest) {
        Map result = sqlExecuteService.sqlExecute(scriptDbAo2ScriptDbBo.convert(scriptRequest));
        if (!result.isEmpty() &&  (Integer)result.get("error") > 0){
            return new JsonResult("操作失败", 500, result,false );
        }
        return new JsonResult("操作成功",200,result,true);
    }
}
