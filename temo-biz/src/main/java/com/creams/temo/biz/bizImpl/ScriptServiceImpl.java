package com.creams.temo.biz.bizImpl;

import com.creams.temo.biz.ScriptService;
import com.creams.temo.convert.DatabaseDto2DatabaseBo;
import com.creams.temo.convert.ScriptDbDto2ScriptDbBo;
import com.creams.temo.mapper.DatabaseMapper;
import com.creams.temo.mapper.ScriptMapper;
import com.creams.temo.model.ScriptDbBo;
import com.creams.temo.model.ScriptDbDto;
import com.creams.temo.tools.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScriptServiceImpl implements ScriptService {
    @Autowired
    private ScriptMapper scriptMapper;

    @Autowired
    private DatabaseMapper databaseMapper;
    final ScriptDbDto2ScriptDbBo scriptDbBo2ScriptDbDto =ScriptDbDto2ScriptDbBo.getInstance();
    final DatabaseDto2DatabaseBo databaseDto2DatabaseBo = DatabaseDto2DatabaseBo.getInstance();
    /**
     * 查询所有Script
     * @return
     */
    public List<ScriptDbBo> queryAllScript(){
        return Lists.newArrayList(scriptDbBo2ScriptDbDto.reverse().convertAll(scriptMapper.queryAllScript()));
    }

    /***
     * 根据dbId和scriptName获取对应脚本所属数据库
     * @param dbId
     * @param scriptName
     * @return
     */

    public PageInfo<ScriptDbBo> queryScriptDbByNameAndDbId(Integer page, String dbId, String scriptName){
        PageHelper.startPage(page, 10);
        List<ScriptDbDto> scriptDbDtos = scriptMapper.queryScriptDb(dbId, scriptName);
        PageInfo<ScriptDbBo> pageInfo= new PageInfo<>(Lists.newArrayList(scriptDbBo2ScriptDbDto.reverse().convertAll(scriptDbDtos)));
        pageInfo.getList().forEach(n->n.setDb(databaseDto2DatabaseBo.convert(databaseMapper.queryDatabaseById(n.getDbId()))));
        return pageInfo;
    }

    /**
     * 查询Script详情
     * @param scriptId
     * @return
     */
    public ScriptDbBo queryScriptById(String scriptId){

        ScriptDbDto scriptResponse = scriptMapper.queryScriptById(scriptId);
        return scriptDbBo2ScriptDbDto.reverse().convert(scriptResponse);
    }

    /**
     * 获取envId下的所有脚本
     * @param envId
     * @return
     */
    public PageInfo<ScriptDbBo> queryScriptByEnvId(Integer page, String envId){
        //设置分页数据
        PageHelper.startPage(page, 10);
        List<ScriptDbDto> scriptResponseList = scriptMapper.queryScriptByEnvId(envId);
        PageInfo<ScriptDbBo> pageInfo = new PageInfo<>(Lists.newArrayList(scriptDbBo2ScriptDbDto.reverse().convertAll(scriptResponseList)));
        return pageInfo;
    }

    /**
     * 新增Script
     * @param scriptRequest
     * @return
     */
    @Transactional
    public String addScript(ScriptDbBo scriptRequest){

        String scriptId = StringUtil.uuid();
        scriptRequest.setScriptId(scriptId);
        scriptMapper.addScript(scriptDbBo2ScriptDbDto.convert(scriptRequest));
        return scriptId;
    }

    /**
     * 修改Script
     * @param scriptRequest
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateScriptById(ScriptDbBo scriptRequest){
       scriptMapper.updateScriptById(scriptDbBo2ScriptDbDto.convert(scriptRequest));
    }

    /**
     * 删除Script信息
     * @param scriptId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteScriptById(String scriptId){
        scriptMapper.deleteScriptById(scriptId);
    }
}
