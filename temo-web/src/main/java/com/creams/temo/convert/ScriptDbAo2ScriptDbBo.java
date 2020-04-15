package com.creams.temo.convert;

import com.creams.temo.model.DatabaseAo;
import com.creams.temo.model.DatabaseBo;
import com.creams.temo.model.ScriptDbAo;
import com.creams.temo.model.ScriptDbBo;
import com.google.common.base.Converter;

public class ScriptDbAo2ScriptDbBo extends Converter<ScriptDbAo,ScriptDbBo> {

    public ScriptDbAo2ScriptDbBo(){}
    public static ScriptDbAo2ScriptDbBo getInstance() {
        return ScriptDbAo2ScriptDbBo.SingletonHolder.INSTANCE;
    }
    private static class SingletonHolder {
        private static final ScriptDbAo2ScriptDbBo INSTANCE = new ScriptDbAo2ScriptDbBo();
    }

    @Override
    protected ScriptDbBo doForward(ScriptDbAo scriptDbAo) {
        return ScriptDbBo.builder()
                .createTime(scriptDbAo.getCreateTime())
                .Db(DatabaseBo.builder().user(scriptDbAo.getDb().getUser())
                        .updatetime(scriptDbAo.getDb().getUpdatetime())
                        .pwd(scriptDbAo.getDb().getPwd())
                        .port(scriptDbAo.getDb().getPort())
                        .id(scriptDbAo.getDb().getId())
                        .host(scriptDbAo.getDb().getHost())
                        .dbName(scriptDbAo.getDb().getDbName())
                        .dbLibraryName(scriptDbAo.getDb().getDbLibraryName())
                        .dbId(scriptDbAo.getDb().getDbId())
                        .createtime(scriptDbAo.getDb().getCreatetime()).build())
                .dbId(scriptDbAo.getDbId())
                .id(scriptDbAo.getId())
                .scriptId(scriptDbAo.getScriptId())
                .scriptName(scriptDbAo.getScriptName())
                .scriptId(scriptDbAo.getScriptId())
                .updateTime(scriptDbAo.getUpdateTime())
                .build();
    }

    @Override
    protected ScriptDbAo doBackward(ScriptDbBo scriptDbBo) {
        return ScriptDbAo.builder()
                .createTime(scriptDbBo.getCreateTime())
                .Db(DatabaseAo.builder().user(scriptDbBo.getDb().getUser())
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
}
