package com.creams.temo.mapper;

import com.creams.temo.entity.ExecutedRow;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExecuteRowMapper {

    void addExecutedRow(ExecutedRow executedRow);

    Integer queryMaxIndexOfExecutedRow(String setId);

    List<ExecutedRow> queryExecutedRowBySetId(String setId);
}
