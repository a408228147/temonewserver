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
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.*;

@Service
public class SqlExecuteServiceImpl implements SqlExecuteService {
    private static Logger logger = LoggerFactory.getLogger("fileInfoLog");

    @Autowired
    private DatabaseMapper databaseMapper;
    final DatabaseDto2DatabaseBo databaseDto2DatabaseBo = DatabaseDto2DatabaseBo.getInstance();

    @Override
    public void testConnect(DatabaseBo databaseInfo) {
        // test mysql 链接
        if ("100".equals(databaseInfo.getDbType())){
            // 构建数据库实例
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            dataSource.setUrl(String.format("jdbc:mysql://%s:%d/%s?characterEncoding=utf-8", databaseInfo.getHost(), databaseInfo.getPort(),
                    databaseInfo.getDbLibraryName()));
            dataSource.setUsername(databaseInfo.getUser());
            dataSource.setPassword(databaseInfo.getPwd());
            DataSourceUtils.getConnection(dataSource);
            // test reids 链接
        }else if ("200".equals(databaseInfo.getDbType())){
            //创建Jedis实例
            Jedis jedis = new Jedis(databaseInfo.getHost(), databaseInfo.getPort());
            if (databaseInfo.getPwd() != null && !databaseInfo.getPwd().isEmpty()){
                jedis.auth(databaseInfo.getPwd());
            }
            jedis.ping();
        }
    }


    /**
     * 获取mysql连接池
     * @param dbId
     * @return
     */
    @Override
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
     * 获取Jedis实例
     * @param dbId
     * @return
     */
    @Override
    public Jedis getJedis(String dbId) {
        DatabaseDto databaseInfo = databaseMapper.queryDatabaseById(dbId);
        DatabaseBo databaseBo = databaseDto2DatabaseBo.convert(databaseInfo);
        Jedis jedis = new Jedis(databaseBo.getHost(), databaseBo.getPort());
        //设置redis密码
        if (databaseBo.getPwd() != null && !(databaseBo.getPwd().length() <=0)) {
            jedis.auth(databaseBo.getPwd());
        }
        // 选择redis库 当db为redis-getDbLibraryName--> 0-16
        String index = databaseBo.getDbLibraryName();
        if (index !=null && !index.isEmpty() && ( 0<=Integer.parseInt(index)  && Integer.parseInt(index) <17)){
            jedis.select(Integer.parseInt(databaseBo.getDbLibraryName()));
            logger.info("当前的redis库为=====>" + databaseBo.getDbLibraryName());
        }
        return jedis;
    }


    /**
     * 执行sql调试脚本
     *
     * @param scriptRequest
     * @return
     */
    @Override
    public Map sqlExecute(ScriptDbBo scriptRequest) {
        Map<String, Object> executeResult = new HashMap<>();
        if ("100".equals(databaseMapper.queryDatabaseById(scriptRequest.getDbId()).getDbType())){
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
            executeResult.put("total", total);
            executeResult.put("success", total - error);
            executeResult.put("error", error);
            executeResult.put("errorList", errorList);
            return executeResult;

        }else if ("200".equals(databaseMapper.queryDatabaseById(scriptRequest.getDbId()).getDbType())){
            Jedis jedis = this.getJedis(scriptRequest.getDbId());
            //1. 遍历sql列表，执行sql
            List<SqlScript> sqlScripts = JSON.parseArray(scriptRequest.getSqlScript(), SqlScript.class);
            Integer total = sqlScripts.size();
            int error = 0;
            List<Map<String, Object>> errorList = new LinkedList<>();
            for (SqlScript redisScript : sqlScripts) {
                // 捕获redis执行，发生异常时，error数+1
                try {
                    if (redisScript.getScript().toLowerCase().trim().startsWith("set")){
                        List<String> list = Arrays.asList(redisScript.getScript().split("\\s+"));
                        if (list.size() == 3){
                            jedis.set(list.get(1), list.get(2));
                        }else {
                            error++;
                            Map<String, Object> errorDetail = new HashMap<>();
                            errorDetail.put("redis", redisScript.getScript());
                            errorDetail.put("errMsg", "ERROR: Invalid command");
                            errorList.add(errorDetail);

                        }
                    }
                    logger.info("redis=====>" + redisScript.getScript() + " 执行成功");
                } catch (Exception e) {
                    logger.error("redis=====>" + redisScript.getScript() + " 执行异常！错误原因：" + e);
                    error++;
                    Map<String, Object> errorDetail = new HashMap<>();
                    errorDetail.put("redis", redisScript.getScript());
                    errorDetail.put("errMsg", e.getMessage());
                    errorList.add(errorDetail);
                }
            }
            executeResult.put("total", total);
            executeResult.put("success", total - error);
            executeResult.put("error", error);
            executeResult.put("errorList", errorList);
            return executeResult;

        }
        return executeResult;
    }
}
