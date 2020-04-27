package com.creams.temo.biz;

import com.creams.temo.model.DatabaseBo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface DatabaseService {
    List<DatabaseBo> queryAllDatabase(String dbType);
    PageInfo<DatabaseBo> queryDatabaseByName(Integer page, String filter);
    DatabaseBo queryDatabaseById(String dbId);
    String addDatabase(DatabaseBo databaseBo);
    void updateDatabaseById(DatabaseBo databaseBo);
    void deleteDatabaseById(String dbId);
}
