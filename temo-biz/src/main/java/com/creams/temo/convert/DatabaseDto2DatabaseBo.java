package com.creams.temo.convert;

import com.creams.temo.model.DatabaseBo;
import com.creams.temo.model.DatabaseDto;
import com.google.common.base.Converter;

public class DatabaseDto2DatabaseBo extends Converter<DatabaseDto,DatabaseBo> {


    private DatabaseDto2DatabaseBo() {
    }

    public static DatabaseDto2DatabaseBo getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final DatabaseDto2DatabaseBo INSTANCE = new DatabaseDto2DatabaseBo();
    }
    @Override
    protected DatabaseBo doForward(DatabaseDto databaseDto) {
        if(databaseDto==null){
            return null;
        }
        return  DatabaseBo.builder()
                .createtime(databaseDto.getCreatetime())
                .dbId(databaseDto.getDbId())
                .dbLibraryName(databaseDto.getDbLibraryName())
                .dbName(databaseDto.getDbName())
                .host(databaseDto.getHost())
                .id(databaseDto.getId())
                .port(databaseDto.getPort())
                .pwd(databaseDto.getPwd())
                .updatetime(databaseDto.getUpdatetime())
                .user(databaseDto.getUser()).build();
    }

    @Override
    protected DatabaseDto doBackward(DatabaseBo databaseBo) {
        if(databaseBo==null){
            return null;
        }
        return DatabaseDto.builder()
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
