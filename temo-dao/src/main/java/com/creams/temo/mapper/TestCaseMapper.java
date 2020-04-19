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

    @Select("SELECT * from testcase WHERE set_id=#{setId} and sorting<#{sorting} ORDER BY sorting DESC LIMIT 1")
    TestCase queryTestCaseUpBySorting(String setId, Integer sorting);

    @Select("SELECT * from testcase WHERE set_id=#{setId} and sorting>#{sorting} ORDER BY sorting LIMIT 1")
    TestCase queryTestCaseDownBySorting(String setId, Integer sorting);

    @Select("select min(sorting) from testcase where set_id = #{setId}")
    Integer queryMinSorting(String setId);

    @Select("select max(sorting) from testcase where set_id = #{setId} ")
    Integer queryMaxSorting(String setId);

    @Select("select * from testcase where case_id = #{case_id}")
    TestCase queryTestCaseById(@Param("case_id") String caseId);

    @Select("select * from testcase where case_id = #{case_id}")
    TestCase queryCopyTestCaseById(@Param("case_id") String caseId);


    @Select("select * from testcase where set_id = #{set_id} order by sorting asc")
    List<TestCase> queryTestCaseBySetId(@Param("set_id") String setId);

    @Select("select * from testcase where set_id = #{set_id} order by sorting asc")
    List<TestCase> queryCopyTestCaseBySetId(@Param("set_id") String setId);

    @Select("SELECT COUNT(*) FROM testcase WHERE user_id = #{user_id}")
    Integer statisticsTestCaseByUserId(@Param("user_id") String userId);

    void addTestCase(TestCase testCaseRequest);

    void updateTestCaseById(TestCase testCaseRequest);

    void updateTestCaseOrderById(String caseId, Integer sorting);

    @Delete("delete from testcase where case_id = #{case_id}")
    void deleteTestCase(@Param("case_id") String caseId);


}