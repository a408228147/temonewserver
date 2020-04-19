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
                .Db(DatabaseDto.builder().user(scriptDbBo.getDb().getUser())
                        .updatetime(scriptDbBo.getDb().getUpdatetime())
                        .pwd(scriptDbBo.getDb().getPwd())
                        .port(scriptDbBo.getDb().getPort())
                        .id(scriptDbBo.getDb().getId())
                        .host(scriptDbBo.getDb().getHost())
                        .dbName(scriptDbBo.getDb().getDbName())
                        .dbLibraryName(scriptDbBo.getDb().getDbLibraryName())
                        .dbId(scriptDbBo.getDb().getDbId())
                        .createtime(scriptDbBo.getDb().getCreatetime()).build())
                .dbId(scriptDbBo.getDbId())
                .id(scriptDbBo.getId())
                .scriptId(scriptDbBo.getScriptId())
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
                .Db(DatabaseBo.builder().user(scriptDbDto.getDb().getUser())
                        .updatetime(scriptDbDto.getDb().getUpdatetime())
                        .pwd(scriptDbDto.getDb().getPwd())
                        .port(scriptDbDto.getDb().getPort())
                        .id(scriptDbDto.getDb().getId())
                        .host(scriptDbDto.getDb().getHost())
                        .dbName(scriptDbDto.getDb().getDbName())
                        .dbLibraryName(scriptDbDto.getDb().getDbLibraryName())
                        .dbId(scriptDbDto.getDb().getDbId())
                        .createtime(scriptDbDto.getDb().getCreatetime()).build())
                .dbId(scriptDbDto.getDbId())
                .id(scriptDbDto.getId())
                .scriptId(scriptDbDto.getScriptId())
                .scriptName(scriptDbDto.getScriptName())
                .scriptId(scriptDbDto.getScriptId())
                .updateTime(scriptDbDto.getUpdateTime())
                .build();
    }
}
