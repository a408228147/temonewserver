package com.creams.temo.biz;

import com.creams.temo.model.DatabaseBo;
import com.creams.temo.model.ScriptDbBo;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import redis.clients.jedis.Jedis;

import java.util.Map;

public interface SqlExecuteService {
   void testConnect(DatabaseBo databaseBo);
   Map sqlExecute(ScriptDbBo scriptDbBo);

   DriverManagerDataSource getDataSource(String dbId);

   Jedis getJedis(String dbId);
}
