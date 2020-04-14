package com.creams.temo.convert;

import com.creams.temo.ProjectBo;
import com.creams.temo.model.ProjectDto;
import com.google.common.base.Converter;

public class ProjectBo2ProjectDto extends Converter<ProjectBo,ProjectDto> {

    public ProjectBo2ProjectDto(){}

    public static ProjectBo2ProjectDto getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final ProjectBo2ProjectDto INSTANCE = new ProjectBo2ProjectDto();
    }
    @Override
    protected ProjectDto doForward(ProjectBo projectBo) {
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
        return ProjectBo.builder()
                .id(projectDto.getId())
                .createTime(projectDto.getCreateTime())
                .envs(projectDto.getEnvs())
                .pid(projectDto.getPid())
                .pname(projectDto.getPname())
                .updateTime(projectDto.getUpdateTime()).build();
    }
}
