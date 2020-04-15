package com.creams.temo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TasksInfoMapper {


    Integer queryTaskNum();


    Integer queryTaskIsTimingNum();

    Integer queryTaskStatusIsEnd();

    Integer queryTaskStatusIsStart();

}
