package com.creams.temo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ExecuteTimeInfoMapper {

        /**
         * 查询平台运行时间之和：秒
         * @return
         */
        Integer queryExecuteTime();

        /**
         * 正在执行任务
         * @return
         */
        Integer queryExecuteTaskNumNow();


}
