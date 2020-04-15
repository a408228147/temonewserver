package com.creams.temo.biz.bizImpl;

import com.creams.temo.biz.TestCaseSetInfoService;
import com.creams.temo.mapper.TestCaseSetInfoMaper;
import com.creams.temo.model.TesSetInfoBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestCaseSetInfoServiceImpl implements TestCaseSetInfoService {

    @Autowired
    TestCaseSetInfoMaper testCaseSetInfoMaper;

    /**
     * 查询用例库信息
     * @return
     */
    public TesSetInfoBo queryTestSetInfo(){
        TesSetInfoBo tesSetInfoResponse =TesSetInfoBo.builder()
                .testCaseSetNum(testCaseSetInfoMaper.queryTestCaseSetNum())
                .testCaseNum(testCaseSetInfoMaper.queryTestCaseNum()).build();
        return tesSetInfoResponse;
    }
}
