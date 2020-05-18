package com.creams.temo.biz.bizImpl;

import com.creams.temo.biz.FuncToolsService;
import com.creams.temo.convert.FuncToolsDto2FuncToolsBo;
import com.creams.temo.entity.UserInfo;
import com.creams.temo.mapper.FuncToolsMapper;
import com.creams.temo.model.FuncToolsBo;
import com.creams.temo.model.UserBo;
import com.creams.temo.tools.ShiroUtils;
import com.google.common.collect.Lists;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FuncToolsServiceImpl implements FuncToolsService {

    @Autowired
    FuncToolsMapper funcToolsMapper;

    final FuncToolsDto2FuncToolsBo funcToolsDto2FuncToolsBo = FuncToolsDto2FuncToolsBo.getInstance();

    @Override
    @Transactional
    public void saveFunc(FuncToolsBo funcToolsBo) {
            funcToolsMapper.saveFunc(funcToolsDto2FuncToolsBo.reverse().convert(funcToolsBo));
    }

    @Override
    public List<FuncToolsBo> findFunc(FuncToolsBo funcToolsBo) {
        return Lists.newArrayList(funcToolsDto2FuncToolsBo.convertAll(funcToolsMapper.findFunc(funcToolsDto2FuncToolsBo.reverse().convert(funcToolsBo))));
    }

    @Override
    @Transactional
    public void updateFuncById(FuncToolsBo funcToolsBo) {
        funcToolsMapper.updateFuncById(funcToolsDto2FuncToolsBo.reverse().convert(funcToolsBo));
    }

    @Override
    @Transactional
    public void deleteFuncById(Integer id) {
        UserInfo user = ShiroUtils.getUserEntity();
        funcToolsMapper.deleteFuncById(id ,user.getUserName());
    }

}
