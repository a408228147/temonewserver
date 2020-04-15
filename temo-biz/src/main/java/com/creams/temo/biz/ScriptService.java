package com.creams.temo.biz;

import com.creams.temo.model.ScriptDbBo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ScriptService {
    List<ScriptDbBo> queryAllScript();
    PageInfo<ScriptDbBo> queryScriptDbByNameAndDbId(Integer page, String dbId, String scriptName);
    ScriptDbBo queryScriptById(String scriptId);
    String addScript(ScriptDbBo scriptDbBo);
    void updateScriptById(ScriptDbBo scriptId);
    void deleteScriptById(String scriptId);
}
