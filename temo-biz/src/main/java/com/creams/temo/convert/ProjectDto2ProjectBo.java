package com.creams.temo.convert;

import com.creams.temo.model.ProjectBo;
import com.creams.temo.model.ProjectDto;
import com.google.common.base.Converter;

public class ProjectDto2ProjectBo extends Converter<ProjectBo,ProjectDto> {

    public ProjectDto2ProjectBo(){}

    public static ProjectDto2ProjectBo getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final ProjectDto2ProjectBo INSTANCE = new ProjectDto2ProjectBo();
    }
    @Override
    protected ProjectDto doForward(ProjectBo projectBo) {
        if (projectBo==null){
            return null;
        }
        return ProjectDto.builder()
                .id(projectBo.getId())
                .createTime(projectBo.getCreateTime())
                .envs(projectBo.getEnvs())
                .pid(projectBo.getPid())
                .pname(projectBo.getPname())
                .updateTime(projectBo.getUpdateTime()).build();
    }

    @Override
    protected ProjectBo doBackward(ProjectDto projectDto) {
        if (projectDto==null){
            return null;
        }
        return ProjectBo.builder()
                .id(projectDto.getId())
                .createTime(projectDto.getCreateTime())
                .envs(projectDto.getEnvs())
                .pid(projectDto.getPid())
                .pname(projectDto.getPname())
                .updateTime(projectDto.getUpdateTime()).build();
    }
}
