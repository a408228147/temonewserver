package com.creams.temo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TestCaseSetInfoMaper {

    /**
     * 查询用例集启用数量
     * @return
     */
    Integer queryTestCaseSetNum();

    /**
     * 查询用例集启用关联用例数量
     * @return
     */
    Integer queryTestCaseNum();

}
