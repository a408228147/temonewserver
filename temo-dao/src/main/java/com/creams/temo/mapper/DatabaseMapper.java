package com.creams.temo.mapper;

import com.creams.temo.model.DatabaseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DatabaseMapper {
    List<DatabaseDto> queryAllDatabase();

    List<DatabaseDto> queryDatabase(@Param("db_name") String name);
    DatabaseDto queryDatabaseById(@Param("db_id") String dbId);

    void addDatabase(DatabaseDto databaseDto);
    void updateDatabaseById(DatabaseDto databaseDto);
    void deteleDatabaseById(@Param("db_id") String dbId);
}
