package com.creams.temo.biz;

import com.creams.temo.model.ExecuteTodayBo;

public interface ExecuteTodayService {
    ExecuteTodayBo queryTodayExecuteTaskInfo();
    ExecuteTodayBo queryTodayExecuteTestCaseInfo();
}
