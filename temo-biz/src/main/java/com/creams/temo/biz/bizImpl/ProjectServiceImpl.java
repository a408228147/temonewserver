package com.creams.temo.biz.bizImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.creams.temo.convert.ProjectDto2ProjectBo;
import com.creams.temo.entity.Env;
import com.creams.temo.biz.ProjectService;
import com.creams.temo.model.ProjectBo;
import com.creams.temo.mapper.EnvMapper;
import com.creams.temo.mapper.ProjectMapper;
import com.creams.temo.model.ProjectDto;
import com.creams.temo.tools.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {


    final ProjectDto2ProjectBo projectDto2ProjectBo = ProjectDto2ProjectBo.getInstance();
    @Autowired
    private ProjectMapper projectMapper;


    @Autowired
    private EnvMapper envMapper;

    /**
     * 新增项目及其环境
     *
     * @param project
     * @return
     */
    @Override
    @Transactional
    public String addProject(ProjectBo project) {
        String projectId = StringUtil.uuid();
        project.setPid(projectId);
        ProjectDto projectDto = projectDto2ProjectBo.convert(project);
        projectMapper.insert(projectDto);
        // 判断环境是否为空
        if (project.getEnvs() != null) {
            project.getEnvs().forEach(e -> {
                e.setEnvId(StringUtil.uuid());
                e.setProjectId(project.getPid());
                e.setProjectId(projectId);
                envMapper.insert(e);
            });
        }
        return projectId;
    }

    /**
     * 查询项目列表
     *
     * @return
     */
    @Override
    public List<ProjectBo> queryAllProjects() {
        List<ProjectDto> projectDtos = projectMapper.queryAllProject();
        List<ProjectBo> projectBos = Lists.newArrayList(projectDto2ProjectBo.reverse().convertAll(projectDtos));
        return projectBos;
    }

    /**
     * 根据项目id查询所属环境
     *
     * @param projectId
     * @return
     */
    @Override
    public List<Env> queryEnvByProjectId(String projectId) {
        List<Env> envResponses = envMapper.queryEnvByProjectId(projectId);
        return envResponses;
    }

    /**
     * 模糊查询项目
     *
     * @param name
     * @return
     */
    @Override
    public PageInfo<ProjectBo> queryByName(Integer page, String name) {
        PageHelper.startPage(page, 10);
        List<ProjectDto> projects = projectMapper.queryProjectByName(name);
        List<ProjectBo> projectBos = Lists.newArrayList(projectDto2ProjectBo.reverse().convertAll(projects));
        PageInfo<ProjectBo> pageInfo = new PageInfo<>(projectBos);
        pageInfo.getList().forEach(p -> p.setEnvs(envMapper.queryEnvByProjectId(p.getPid())));
        return pageInfo;
    }

    /**
     * 查看项目详情
     *
     * @param projectId
     * @return
     */
    @Override
    public ProjectBo queryDetailById(String projectId) {
        ProjectDto projectDto = projectMapper.queryProjectById(projectId);
        ProjectBo projectBo = projectDto2ProjectBo.reverse().convert(projectDto);
        projectBo.setEnvs(envMapper.queryEnvByProjectId(projectId));
        return projectBo;
    }

    /**
     * 删除项目
     *
     * @param projectId
     * @return
     */
    @Override
    @Transactional
    public Integer delProjectById(String projectId) {
        int i = projectMapper.delete(new QueryWrapper<ProjectDto>().lambda().eq(ProjectDto::getPid, projectId));
        envMapper.delete(new QueryWrapper<Env>().lambda().eq(Env::getProjectId, projectId));
        return i;
    }

    /**
     * 编辑项目
     *
     * @param projectId
     * @param project
     * @return
     */
    @Override
    @Transactional
    public void updateProjectById(String projectId, ProjectBo project) {
        ProjectDto projectDto = projectDto2ProjectBo.convert(project);
        projectMapper.update(projectDto, new QueryWrapper<ProjectDto>().lambda().eq(ProjectDto::getPid, projectId));
        List<String> envIds = new ArrayList<>();
        List<String> requestEnvIds = new ArrayList<>();
        List<Env> envResponses = envMapper.queryEnvByProjectId(projectId);
        envResponses.forEach(n -> envIds.add(n.getEnvId()));
        project.getEnvs().forEach(e -> requestEnvIds.add(e.getEnvId()));
        for (Env env : project.getEnvs()) {
            env.setProjectId(project.getPid());
            // 更新项目下的环境
            if (env.getEnvId() == null) {
                env.setEnvId(StringUtil.uuid());
                envMapper.insert(env);
            } else {
                envMapper.update(env, new QueryWrapper<Env>().lambda().eq(Env::getEnvId, env.getEnvId()));
            }
        }
        for (String envId : envIds) {
            if (requestEnvIds.indexOf(envId) < 0) {
                envMapper.delete(new QueryWrapper<Env>().lambda().eq(Env::getEnvId, envId));
            }
        }
    }

}
