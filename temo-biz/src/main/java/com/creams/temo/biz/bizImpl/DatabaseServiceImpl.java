package com.creams.temo.biz.bizImpl;

import com.creams.temo.biz.DatabaseService;
import com.creams.temo.convert.DatabaseDto2DatabaseBo;
import com.creams.temo.mapper.DatabaseMapper;
import com.creams.temo.model.DatabaseBo;
import com.creams.temo.tools.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DatabaseServiceImpl implements DatabaseService {
    @Autowired
    private DatabaseMapper databaseMapper;
    final DatabaseDto2DatabaseBo databbaseDto2DatabaseBo =DatabaseDto2DatabaseBo.getInstance();

    @Override
    public List<DatabaseBo> queryAllDatabase(String dbType){
        return Lists.newArrayList(databbaseDto2DatabaseBo.convertAll(databaseMapper.queryAllDatabase(dbType)));
    }

    /**
     * 分页查询数据库配置信息
     * @return
     */
    @Override
    public PageInfo<DatabaseBo> queryDatabaseByName(Integer page, String name,String type){
        PageHelper.startPage(page, 10);
        List<DatabaseBo> databaseResponses =Lists.newArrayList(databbaseDto2DatabaseBo.convertAll(databaseMapper.queryDatabase(name,type)));
        return new PageInfo<>(databaseResponses);
    }

    /**
     * 查询database详情
     * @param dbId
     * @return
     */
    @Override
    public DatabaseBo queryDatabaseById(String dbId){
        return  databbaseDto2DatabaseBo.convert(databaseMapper.queryDatabaseById(dbId));
    }

    /**
     * 新增database
     * @param databaseBo
     * @return
     */
    @Override
    @Transactional
    public String addDatabase(DatabaseBo databaseBo){
        String dbId = StringUtil.uuid();
        databaseBo.setDbId(dbId);
        databaseMapper.addDatabase(databbaseDto2DatabaseBo.reverse().convert(databaseBo));
        return dbId;
    }

    /**
     * 修改database
     * @param databaseBo
     * @return
     */
    @Override
    @Transactional
    public void updateDatabaseById(DatabaseBo databaseBo){
          databaseMapper.updateDatabaseById(databbaseDto2DatabaseBo.reverse().convert(databaseBo));
    }

    /**
     * 删除数据库信息
     * @param dbId
     * @return
     */
    @Override
    @Transactional
    public void deleteDatabaseById(String dbId){
          databaseMapper.deteleDatabaseById(dbId);
    }
}
