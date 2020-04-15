package com.creams.temo.biz.bizImpl;

import com.creams.temo.biz.ExecuteTimeInfoService;
import com.creams.temo.mapper.ExecuteTimeInfoMapper;
import com.creams.temo.model.ExecuteTimeInfoBo;
import com.creams.temo.tools.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExecuteTimeInfoServiceImpl implements ExecuteTimeInfoService {

    @Autowired
    ExecuteTimeInfoMapper executeTimeInfoMapper;

    /**
     * 查询自动化运行信息
     *
     * @return
     */
    public ExecuteTimeInfoBo queryExecuteTimeInfo() {
        ExecuteTimeInfoBo executeTimeInfoResponse = ExecuteTimeInfoBo.builder()
                .executeTime(DateUtil.getDate(executeTimeInfoMapper.queryExecuteTime()))
                .executeTaskNumNow(executeTimeInfoMapper.queryExecuteTaskNumNow()).build();
        return executeTimeInfoResponse;
    }
}
