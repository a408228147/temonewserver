package com.creams.temo.mapper;

import com.creams.temo.model.ExecuteSevenDaysDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SevenDaysExecuteInfoMapper {

    ExecuteSevenDaysDto querySevenDaysTestCaseSuccessNum();

}
