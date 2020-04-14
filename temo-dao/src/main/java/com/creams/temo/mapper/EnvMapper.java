package com.creams.temo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.creams.temo.entity.Env;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EnvMapper extends BaseMapper<Env> {
    List<Env> queryEnvByProjectId(@Param("project_id") String projectId);
    Env queryEnvById(@Param("env_id") String envId);
}
