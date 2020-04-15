package com.creams.temo.biz.bizImpl;

import com.alibaba.fastjson.JSON;
import com.creams.temo.biz.SqlExecuteService;
import com.creams.temo.convert.DatabaseDto2DatabaseBo;
import com.creams.temo.entity.SqlScript;
import com.creams.temo.mapper.DatabaseMapper;
import com.creams.temo.model.DatabaseBo;
import com.creams.temo.model.DatabaseDto;
import com.creams.temo.model.ScriptDbBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class SqlExecuteServiceImpl implements SqlExecuteService {
    private static Logger logger = LoggerFactory.getLogger("fileInfoLog");

    @Autowired
    private DatabaseMapper databaseMapper;
    final DatabaseDto2DatabaseBo databaseDto2DatabaseBo = DatabaseDto2DatabaseBo.getInstance();

    public void testConnect(DatabaseBo databaseInfo) {
        // 构建数据库实例
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(String.format("jdbc:mysql://%s:%d/%s?characterEncoding=utf-8", databaseInfo.getHost(), databaseInfo.getPort(),
                databaseInfo.getDbLibraryName()));
        dataSource.setUsername(databaseInfo.getUser());
        dataSource.setPassword(databaseInfo.getPwd());
        DataSourceUtils.getConnection(dataSource);
    }

    public DriverManagerDataSource getDataSource(String dbId) {
        DatabaseDto databaseInfo = databaseMapper.queryDatabaseById(dbId);
        DatabaseBo databaseBo = databaseDto2DatabaseBo.convert(databaseInfo);
        // 构建数据库实例
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(String.format("jdbc:mysql://%s:%d/%s?characterEncoding=utf-8", databaseBo.getHost(), databaseBo.getPort(),
                databaseBo.getDbLibraryName()));
        dataSource.setUsername(databaseBo.getUser());
        dataSource.setPassword(databaseBo.getPwd());
        return dataSource;
    }


    /**
     * 执行sql调试脚本
     *
     * @param scriptRequest
     * @return
     */
    public Map sqlExecute(ScriptDbBo scriptRequest) {
        //1.拿到数据库实例
        DriverManagerDataSource dataSource = getDataSource(scriptRequest.getDbId());
        //2. 创建jdbctemplate 实例
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        //3. 遍历sql列表，执行sql
        List<SqlScript> sqlScripts = JSON.parseArray(scriptRequest.getSqlScript(), SqlScript.class);
        Integer total = sqlScripts.size();
        int error = 0;
        List<Map<String, Object>> errorList = new LinkedList<>();
        for (SqlScript sqlScript : sqlScripts) {
            // 捕获sql执行，发生异常时，error数+1
            try {
                jdbcTemplate.execute(sqlScript.getScript());
                logger.info("sql=====>" + sqlScript.getScript() + " 执行成功");
            } catch (Exception e) {
                logger.error("sql=====>" + sqlScript.getScript() + " 执行异常！错误原因：" + e);
                error++;
                Map<String, Object> errorDetail = new HashMap<>();
                errorDetail.put("sql", sqlScript.getScript());
                errorDetail.put("errMsg", e.getMessage());
                errorList.add(errorDetail);
            }
        }
        Map<String, Object> executeResult = new HashMap<>();
        executeResult.put("total", total);
        executeResult.put("success", total - error);
        executeResult.put("error", error);
        executeResult.put("errorList", errorList);
        return executeResult;
    }
}
