package com.creams.temo.mapper;

import com.creams.temo.model.ExecuteTodayDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExecuteTodayMapper {

    ExecuteTodayDto queryExecuteTodayInfo();

    ExecuteTodayDto queryExecuteTodayTestCaseInfo();
}

