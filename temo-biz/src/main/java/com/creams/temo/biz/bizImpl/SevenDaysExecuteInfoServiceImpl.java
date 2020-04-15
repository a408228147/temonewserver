package com.creams.temo.biz.bizImpl;

import com.creams.temo.biz.SevenDaysExecuteInfoService;
import com.creams.temo.convert.ExecuteSevenDaysDto2ExecuteSevenDaysBo;
import com.creams.temo.mapper.SevenDaysExecuteInfoMapper;
import com.creams.temo.model.ExecuteSevenDaysBo;
import com.creams.temo.model.ExecuteSevenDaysDto;
import com.creams.temo.model.ExecuteTimeInfoBo;
import com.creams.temo.model.ExecuteTimeInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SevenDaysExecuteInfoServiceImpl implements SevenDaysExecuteInfoService {

    @Autowired
    SevenDaysExecuteInfoMapper sevenDaysExecuteInfoMapper;
    final ExecuteSevenDaysDto2ExecuteSevenDaysBo executeSevenDaysDto2ExecuteSevenDaysBo=ExecuteSevenDaysDto2ExecuteSevenDaysBo.getInstance();
    /**
     * 查询近7日用例执行情况
     * @return
     */
    public ExecuteSevenDaysBo querySevenDaysExecuteInfo(){
        ExecuteSevenDaysDto executeSevenDaysResponse = sevenDaysExecuteInfoMapper.querySevenDaysTestCaseSuccessNum();
        return executeSevenDaysDto2ExecuteSevenDaysBo.convert(executeSevenDaysResponse);
    }
}
