package com.creams.temo.convert;

import com.creams.temo.model.DatabaseBo;
import com.creams.temo.model.DatabaseDto;
import com.creams.temo.model.ScriptDbBo;
import com.creams.temo.model.ScriptDbDto;
import com.google.common.base.Converter;

public class ScriptDbDto2ScriptDbBo extends Converter<ScriptDbBo,ScriptDbDto> {
    private ScriptDbDto2ScriptDbBo() {
    }

    public static ScriptDbDto2ScriptDbBo getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final ScriptDbDto2ScriptDbBo INSTANCE = new ScriptDbDto2ScriptDbBo();
    }
    @Override
    protected ScriptDbDto doForward(ScriptDbBo scriptDbBo) {
        if (scriptDbBo==null){
            return null;
        }
        return ScriptDbDto.builder()
                .createTime(scriptDbBo.getCreateTime())
                .Db(DatabaseDto.builder().user(scriptDbBo.getDb()==null?null:scriptDbBo.getDb().getUser())
                        .updatetime(scriptDbBo.getDb()==null?null:scriptDbBo.getDb().getUpdatetime())
                        .pwd(scriptDbBo.getDb()==null?null:scriptDbBo.getDb().getPwd())
                        .port(scriptDbBo.getDb()==null?null:scriptDbBo.getDb().getPort())
                        .id(scriptDbBo.getDb()==null?null:scriptDbBo.getDb().getId())
                        .host(scriptDbBo.getDb()==null?null:scriptDbBo.getDb().getHost())
                        .dbName(scriptDbBo.getDb()==null?null:scriptDbBo.getDb().getDbName())
                        .dbLibraryName(scriptDbBo.getDb()==null?null:scriptDbBo.getDb().getDbLibraryName())
                        .dbId(scriptDbBo.getDb()==null?null:scriptDbBo.getDb().getDbId())
                        .createtime(scriptDbBo.getDb()==null?null:scriptDbBo.getDb().getCreatetime()).build())
                .dbId(scriptDbBo.getDbId())
                .id(scriptDbBo.getId())
                .sqlScript(scriptDbBo.getSqlScript())
                .scriptName(scriptDbBo.getScriptName())
                .scriptId(scriptDbBo.getScriptId())
                .updateTime(scriptDbBo.getUpdateTime())
                .build();
    }

    @Override
    protected ScriptDbBo doBackward(ScriptDbDto scriptDbDto) {
        if (scriptDbDto==null){
            return null;
        }
        return ScriptDbBo.builder()
                .createTime(scriptDbDto.getCreateTime())
                .Db(DatabaseBo.builder().user(scriptDbDto.getDb()==null?null:scriptDbDto.getDb().getUser())
                        .updatetime(scriptDbDto.getDb()==null?null:scriptDbDto.getDb().getUpdatetime())
                        .pwd(scriptDbDto.getDb()==null?null:scriptDbDto.getDb().getPwd())
                        .port(scriptDbDto.getDb()==null?null:scriptDbDto.getDb().getPort())
                        .id(scriptDbDto.getDb()==null?null:scriptDbDto.getDb().getId())
                        .host(scriptDbDto.getDb()==null?null:scriptDbDto.getDb().getHost())
                        .dbName(scriptDbDto.getDb()==null?null:scriptDbDto.getDb().getDbName())
                        .dbLibraryName(scriptDbDto.getDb()==null?null:scriptDbDto.getDb().getDbLibraryName())
                        .dbId(scriptDbDto.getDb()==null?null:scriptDbDto.getDb().getDbId())
                        .createtime(scriptDbDto.getDb()==null?null:scriptDbDto.getDb().getCreatetime()).build())
                .dbId(scriptDbDto.getDbId())
                .id(scriptDbDto.getId())
                .scriptName(scriptDbDto.getScriptName())
                .sqlScript(scriptDbDto.getSqlScript())
                .scriptId(scriptDbDto.getScriptId())
                .updateTime(scriptDbDto.getUpdateTime())
                .build();
    }
}
