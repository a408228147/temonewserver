package com.creams.temo.biz.bizImpl;

import com.creams.temo.biz.PermissionsService;

import com.creams.temo.convert.PermissionsDto2PermissionsBo;
import com.creams.temo.convert.PermissionsModuleDto2PermissionsModuleBo;
import com.creams.temo.mapper.PermissionsMapper;
import com.creams.temo.model.PermissionsBo;
import com.creams.temo.model.PermissionsDto;
import com.creams.temo.model.PermissionsModuleBo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PermissionsServiceImpl implements PermissionsService {

    @Autowired
    PermissionsMapper permissionsMapper;
    final PermissionsDto2PermissionsBo permissionsDto2PermissionsBo=PermissionsDto2PermissionsBo.getInstance();
    final PermissionsModuleDto2PermissionsModuleBo permissionsModuleDto2PermissionsModuleBo =PermissionsModuleDto2PermissionsModuleBo.getInstance();
    /**
     * 分页查询所有权限
     * @return
     */
    public PageInfo<PermissionsBo> queryPermissionsByPage(Integer page){
        PageHelper.startPage(page, 10);
        List<PermissionsDto> permissions = permissionsMapper.queryPermissions();
        return new PageInfo<>(Lists.newArrayList(permissionsDto2PermissionsBo.convertAll(permissions)));
    }

    /**
     * 根据角色id查询权限
     * @param roleId
     * @return
     */
    public List<PermissionsBo> queryPermissionsByRoleId(String roleId) {
        return Lists.newArrayList(permissionsDto2PermissionsBo.convertAll(permissionsMapper.queryPermissionsByRoleId(roleId)));
    }

    /**
     * 修改权限
     * @param permissions
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public void updatePermission(PermissionsBo permissions){
         permissionsMapper.updatePermission(permissionsDto2PermissionsBo.reverse().convert(permissions));
    }


    /**
     * 设置权限状态
     * @param permissionsId
     * @param status
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public void updatePermissionStatus(String permissionsId, Integer status){
        permissionsMapper.updatePermissionStatus(permissionsId, status);
    }

    @Override
    public List<PermissionsModuleBo> queryPermissonsModule() {
        return Lists.newArrayList(permissionsModuleDto2PermissionsModuleBo.convertAll(permissionsMapper.queryPermissonsModule()));
    }

    @Override
    public List<PermissionsBo> queryPermissionsByModuleId(Integer moduleId) {
        return Lists.newArrayList(permissionsDto2PermissionsBo.convertAll(permissionsMapper.queryPermissionsByModuleId(moduleId)));
    }

    /**
     * 添加权限
     * @param permissions
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public void addPermissions(PermissionsBo permissions){
        permissionsMapper.addPermissions(permissionsDto2PermissionsBo.reverse().convert(permissions));

    }

}
