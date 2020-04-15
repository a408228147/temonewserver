package com.creams.temo.mapper;

import com.creams.temo.model.ScriptDbDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ScriptMapper {

    List<ScriptDbDto> queryAllScript();

    List<ScriptDbDto> queryScriptDb(@Param("db_id")String dbId, @Param("script_name") String scriptName);

    ScriptDbDto queryScriptById(@Param("script_id") String scriptId);

    List<ScriptDbDto> queryScriptByEnvId(@Param("env_id") String envId);

    void addScript(ScriptDbDto scriptRequest);

    void updateScriptById(ScriptDbDto scriptRequest);

    void deleteScriptById(@Param("script_id")String scriptId);
}
