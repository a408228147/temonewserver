package com.creams.temo.biz.bizImpl;

import com.creams.temo.biz.DingdingService;
import com.creams.temo.entity.DingdingEntity;
import com.creams.temo.entity.UserInfo;
import com.creams.temo.mapper.DingdingMapper;
import com.creams.temo.model.UserBo;
import com.creams.temo.tools.ShiroUtils;
import com.creams.temo.tools.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DingdingServiceImpl implements DingdingService {
    @Autowired
    DingdingMapper dingdingMapper;

    /**
     * 查询所有钉钉机器人
     *
     * @return
     */
    public List<DingdingEntity> queryAllDingding() {
        return dingdingMapper.queryAllDingding();
    }

    /**
     * 根据描述模糊查询钉钉机器人
     *
     * @param desc
     * @return
     */
    public List<DingdingEntity> queryDingding(String desc) {
        return dingdingMapper.queryDingding(desc);
    }

    /**
     * 查询单个机器人信息
     *
     * @param descId
     * @return
     */
    public DingdingEntity queryDingdingBydescId(String descId) {
        return dingdingMapper.queryDingdingBydescId(descId);
    }

    /**
     * 修改钉钉机器人
     *
     * @param dingdingEntity
     * @return
     */
    @Transactional
    public void updateDingding(DingdingEntity dingdingEntity) {
        dingdingMapper.updateDingding(dingdingEntity);

    }

    /**
     * 添加钉钉机器人
     *
     * @param
     * @return
     */

    @Transactional
    public void addDingding(DingdingEntity dingdingEntity) {
        dingdingEntity.setDescId(StringUtil.uuid());
        UserInfo user = ShiroUtils.getUserEntity();
        dingdingEntity.setCreater(user.getUserName());
        dingdingMapper.addDingding(dingdingEntity);
    }


    /**
     * 删除钉钉机器人
     *
     * @param descId
     * @return
     */
    @Transactional
    public void deleteDingding(String descId) {
        dingdingMapper.deleteDingding(descId);

    }
}
