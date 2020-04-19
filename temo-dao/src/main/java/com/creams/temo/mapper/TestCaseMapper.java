package com.creams.temo.mapper;

import com.creams.temo.entity.TestCase;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TestCaseMapper {

    List<TestCase> queryTestCase(@Param("case_id") String caseId,
                                 @Param("env_id") String envId,
                                 @Param("set_id") String setId,
                                 @Param("case_desc") String caseDesc,
                                 @Param("db_id") String dbId,
                                 @Param("case_type") String caseType);

    TestCase queryTestCaseUpBySorting(@Param("set_id") String setId, @Param("sorting") Integer sorting);

    TestCase queryTestCaseDownBySorting(@Param("set_id") String setId, @Param("sorting") Integer sorting);

    Integer queryMinSorting(@Param("set_id")String setId);

    Integer queryMaxSorting(@Param("set_id") String setId);

    TestCase queryTestCaseById(@Param("case_id") String caseId);



    List<TestCase> queryTestCaseBySetId(@Param("set_id") String setId);


    Integer statisticsTestCaseByUserId(@Param("user_id") String userId);

    void addTestCase(TestCase testCaseRequest);

    void updateTestCaseById(TestCase testCaseRequest);

    void updateTestCaseOrderById(String caseId, Integer sorting);

    void deleteTestCase(@Param("case_id") String caseId);


}