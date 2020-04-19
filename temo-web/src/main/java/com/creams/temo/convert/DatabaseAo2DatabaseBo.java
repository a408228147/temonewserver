package com.creams.temo.convert;

import com.creams.temo.model.DatabaseAo;
import com.creams.temo.model.DatabaseBo;
import com.google.common.base.Converter;

public class DatabaseAo2DatabaseBo extends Converter<DatabaseAo,DatabaseBo> {

    public DatabaseAo2DatabaseBo(){}
    public static DatabaseAo2DatabaseBo getInstance() {
        return DatabaseAo2DatabaseBo.SingletonHolder.INSTANCE;
    }
    private static class SingletonHolder {
        private static final DatabaseAo2DatabaseBo INSTANCE = new DatabaseAo2DatabaseBo();
    }

    @Override
    protected DatabaseBo doForward(DatabaseAo databaseAo) {
        if (databaseAo==null){
            return null;
        }
        return DatabaseBo.builder()
                .createtime(databaseAo.getCreatetime())
                .dbId(databaseAo.getDbId())
                .dbLibraryName(databaseAo.getDbLibraryName())
                .dbName(databaseAo.getDbName())
                .host(databaseAo.getHost())
                .id(databaseAo.getId())
                .port(databaseAo.getPort())
                .pwd(databaseAo.getPwd())
                .updatetime(databaseAo.getUpdatetime())
                .user(databaseAo.getUser()).build();
    }

    @Override
    protected DatabaseAo doBackward(DatabaseBo databaseBo) {
        if (databaseBo==null){
            return null;
        }
        return DatabaseAo.builder()
                .createtime(databaseBo.getCreatetime())
                .dbId(databaseBo.getDbId())
                .dbLibraryName(databaseBo.getDbLibraryName())
                .dbName(databaseBo.getDbName())
                .host(databaseBo.getHost())
                .id(databaseBo.getId())
                .port(databaseBo.getPort())
                .pwd(databaseBo.getPwd())
                .updatetime(databaseBo.getUpdatetime())
                .user(databaseBo.getUser()).build();
    }
}
