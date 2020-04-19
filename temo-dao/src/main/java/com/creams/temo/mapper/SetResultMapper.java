package com.creams.temo.mapper;

import com.creams.temo.entity.SetResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetResultMapper {

    boolean addSetResult(SetResult setResult);

    List<SetResult> querySetResultsByTaskResultId(String taskResultId);
}
