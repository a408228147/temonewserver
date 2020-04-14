package com.creams.temo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.creams.temo.model.ProjectDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProjectMapper extends BaseMapper<ProjectDto> {
    List<ProjectDto> queryAllProject();

    List<ProjectDto> queryProjectByName(@Param("pname") String name);

    ProjectDto queryProjectById(@Param("pid") String projectId);
}
