package com.creams.temo.biz;

import com.creams.temo.model.FuncToolsBo;

import java.util.List;

public interface FuncToolsService {

    void saveFunc(FuncToolsBo funcToolsBo);

    List<FuncToolsBo> findFunc(FuncToolsBo funcToolsBo);

    void updateFuncById(FuncToolsBo funcToolsBo);

    void deleteFuncById(Integer id);
}
