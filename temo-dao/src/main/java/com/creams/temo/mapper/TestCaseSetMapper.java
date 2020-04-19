package com.creams.temo.mapper;


import com.creams.temo.entity.TestCaseSet;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TestCaseSetMapper {

    List<TestCaseSet> queryTestCaseSetByNameandId(@Param("set_name") String setName,
                                                  @Param("project_id") String projectId,
                                                  @Param("set_status") String setStatus);

    @Select("select * from testcase_set")
    List<TestCaseSet> queryAllTestCaseSet();

    List<TestCaseSet> queryTestCaseSet(TestCaseSet testCaseSetRequest);

    @Select("select * from testcase_set where set_id = #{set_id}")
    TestCaseSet queryTestCaseSetById(@Param("set_id") String setId);

    @Select("select * from testcase_set where set_id = #{set_id}")
    TestCaseSet queryCopyTestCaseSetById(@Param("set_id") String setId);

    @Select("SELECT COUNT(*) FROM testcase_set WHERE user_id = #{user_id}")
    Integer statisticsTestCaseSetByUserId(@Param("user_id") String userId);

    boolean addTestCaseSet(TestCaseSet testCaseSetRequest);

    boolean updateTestCaseSetById(TestCaseSet testCaseSetRequest);

    boolean updateTestCaseSetOfSetUpScript(String setId, String setupScript);

    boolean updateTestCaseSetOfTearDownScript(String setId, String teardownScript);

    @Delete("delete from testcase_set where set_id = #{set_id}")
    boolean deleteTestCaseSetById(@Param("set_id") String setId);
}
