package com.creams.temo.biz;

import com.creams.temo.entity.Env;
import com.creams.temo.entity.TestCaseSet;
import com.creams.temo.entity.TestSet;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.concurrent.Future;

public interface TestCaseSetService {
    TestCaseSet queryTestCaseSetInfo(String setId);

    Boolean executeSetBySynchronizeTask(String taskResultId, TestSet testSet) throws Exception;

    Future<Boolean> executeSetByAsynchronizeTask(String taskResultId, TestSet testSet) throws Exception;

    PageInfo<TestCaseSet> queryTestCaseSetByNameAndId(Integer page, String setName, String projectId, String setStatus);

    List<TestCaseSet> queryAllTestCaseSet();

    Integer statisticsTestCaseSetByUserId(String userId);

    boolean copyTestCaseSet(String setId);

    PageInfo<TestCaseSet> queryTestCaseSet(Integer page, TestCaseSet testCaseSetRequest);

    String addTestCaseSet(TestCaseSet testCaseSetRequest);

    void updateTestCaseSetById(TestCaseSet testCaseSetRequest);

    void deleteTestCaseSetById(String setId);

    void saveSetUpScript(String setId, String setupScript);

    void saveTearDownScript(String setId, String teardownScript);

    List<Env> getEnvsOfSet(String setId);

    void debugSet(String setId, String envId) throws Exception;
}
