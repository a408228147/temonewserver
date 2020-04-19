package com.creams.temo.biz;

import com.creams.temo.entity.TestCase;
import com.github.pagehelper.PageInfo;

public interface TestCaseService {
    PageInfo<TestCase> queryTestCase(Integer page, String caseId, String envId, String setId, String caseDesc, String dbId, String caseType);

    TestCase queryTestCaseInfo(String id);

    Object statisticsTestCaseByUserId(String userId);

    String addTestCase(TestCase testCaseRequest);

    boolean updateTestCase(TestCase testCaseRequest);

    boolean copyTestCase(String caseId);

    boolean updateTestCaseOrderById(String caseId, String move);

    TestCase queryTestCaseById(String caseId);

    void deleteTestCase(String caseId);
}
