package com.creams.temo.biz;

import com.creams.temo.entity.Env;
import com.creams.temo.ProjectBo;

import java.util.List;
import com.github.pagehelper.PageInfo;

public interface ProjectService {
    PageInfo<ProjectBo> queryByName(Integer page, String name);

    String addProject(ProjectBo project);

    List<ProjectBo> queryAllProjects();

    List<Env> queryEnvByProjectId(String projectId);

    ProjectBo queryDetailById(String projectId);

    Integer delProjectById(String projectId);

    String updateProjectById(String projectId,ProjectBo project);

}
