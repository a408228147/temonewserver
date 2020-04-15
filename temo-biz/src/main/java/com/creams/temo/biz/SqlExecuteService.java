package com.creams.temo.biz;

import com.creams.temo.model.DatabaseBo;
import com.creams.temo.model.ScriptDbBo;

import java.util.Map;

public interface SqlExecuteService {
   void testConnect(DatabaseBo databaseBo);
   Map sqlExecute(ScriptDbBo scriptDbBo);
}
