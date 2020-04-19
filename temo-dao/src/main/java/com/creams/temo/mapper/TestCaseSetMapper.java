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

    List<TestCaseSet> queryAllTestCaseSet();

    List<TestCaseSet> queryTestCaseSet(TestCaseSet testCaseSetRequest);

    TestCaseSet queryTestCaseSetById(@Param("set_id") String setId);

    TestCaseSet queryCopyTestCaseSetById(@Param("set_id") String setId);

    Integer statisticsTestCaseSetByUserId(@Param("user_id") String userId);

    void addTestCaseSet(TestCaseSet testCaseSetRequest);

    void updateTestCaseSetById(TestCaseSet testCaseSetRequest);

    void updateTestCaseSetOfSetUpScript(String setId, String setupScript);

    void updateTestCaseSetOfTearDownScript(String setId, String teardownScript);

    void deleteTestCaseSetById(@Param("set_id") String setId);
}
