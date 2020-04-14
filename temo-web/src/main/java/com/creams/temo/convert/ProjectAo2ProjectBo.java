package com.creams.temo.convert;

import com.creams.temo.ProjectBo;
import com.creams.temo.model.ProjectAo;
import com.google.common.base.Converter;

public class ProjectAo2ProjectBo extends Converter<ProjectAo,ProjectBo> {

    public ProjectAo2ProjectBo(){}
    public static ProjectAo2ProjectBo getInstance() {
        return ProjectAo2ProjectBo.SingletonHolder.INSTANCE;
    }
    private static class SingletonHolder {
        private static final ProjectAo2ProjectBo INSTANCE = new ProjectAo2ProjectBo();
    }

    @Override
    protected ProjectBo doForward(ProjectAo projectAo) {
        return ProjectBo.builder()
                .createTime(projectAo.getCreateTime())
                .envs(projectAo.getEnvs())
                .id(projectAo.getId())
                .pid(projectAo.getPid())
                .pname(projectAo.getPname())
                .updateTime(projectAo.getUpdateTime()).build();
    }

    @Override
    protected ProjectAo doBackward(ProjectBo projectBo) {
        return ProjectAo.builder()
                .createTime(projectBo.getCreateTime())
                .envs(projectBo.getEnvs())
                .id(projectBo.getId())
                .pid(projectBo.getPid())
                .pname(projectBo.getPname())
                .updateTime(projectBo.getUpdateTime()).build();
    }
}
