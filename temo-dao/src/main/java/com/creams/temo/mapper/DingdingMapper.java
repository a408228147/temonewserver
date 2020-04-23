package com.creams.temo.mapper;


import com.creams.temo.entity.DingdingEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DingdingMapper {

    List<DingdingEntity> queryAllDingding();

    DingdingEntity queryDingdingBydescId(@Param("desc_id") String descId);

    List<DingdingEntity> queryDingding(String desc);

    void addDingding(DingdingEntity dingdingEntity);

    void updateDingding(DingdingEntity dingdingEntity);

    void deleteDingding(@Param("desc_id") String descId);
}
