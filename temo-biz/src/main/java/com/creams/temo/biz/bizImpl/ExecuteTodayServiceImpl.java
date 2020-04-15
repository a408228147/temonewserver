package com.creams.temo.biz.bizImpl;

import com.creams.temo.biz.ExecuteTodayService;
import com.creams.temo.convert.ExecuteTodayDto2ExecuteTodayBo;
import com.creams.temo.mapper.ExecuteTodayMapper;
import com.creams.temo.model.ExecuteTodayBo;
import com.creams.temo.model.ExecuteTodayDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExecuteTodayServiceImpl implements ExecuteTodayService {

    @Autowired
    ExecuteTodayMapper executeTodayMapper;
    final ExecuteTodayDto2ExecuteTodayBo executeTodayDto2ExecuteTodayBo = ExecuteTodayDto2ExecuteTodayBo.getInstance();
    /**
     * 查询当天用例执行情况
     * @return
     */
    public ExecuteTodayBo queryTodayExecuteTaskInfo(){
        ExecuteTodayDto executeTodayResponse = executeTodayMapper.queryExecuteTodayInfo();
        return executeTodayDto2ExecuteTodayBo.convert(executeTodayResponse);
    }


    /**
     * 查询当天用例成功失败num
     * @return
     */
    public ExecuteTodayBo queryTodayExecuteTestCaseInfo(){
        ExecuteTodayDto executeTodayResponse = executeTodayMapper.queryExecuteTodayTestCaseInfo();
        return executeTodayDto2ExecuteTodayBo.convert(executeTodayResponse);
    }
}
