package com.creams.temo.biz;

import com.creams.temo.entity.TestCaseSet;
import com.creams.temo.entity.TestSet;

import java.util.concurrent.Future;

public interface TestCaseSetService {
    TestCaseSet queryTestCaseSetInfo(String setId);

    Boolean executeSetBySynchronizeTask(String taskResultId, TestSet testSet);

    Future<Boolean> executeSetByAsynchronizeTask(String taskResultId, TestSet testSet);
}
