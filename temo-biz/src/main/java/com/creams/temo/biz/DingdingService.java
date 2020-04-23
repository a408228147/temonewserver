package com.creams.temo.biz;

import com.creams.temo.entity.DingdingEntity;

import java.util.List;

public interface DingdingService {
    List<DingdingEntity> queryAllDingding();

    DingdingEntity queryDingdingBydescId(String descId);

    void addDingding(DingdingEntity dingdingEntity);

    void updateDingding(DingdingEntity dingdingEntity);

    void deleteDingding(String descId);
}
