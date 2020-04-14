package com.creams.temo.biz.bizImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.creams.temo.convert.ProjectBo2ProjectDto;
import com.creams.temo.entity.Env;
import com.creams.temo.biz.ProjectService;
import com.creams.temo.ProjectBo;
import com.creams.temo.mapper.EnvMapper;
import com.creams.temo.mapper.ProjectMapper;
import com.creams.temo.model.ProjectDto;
import com.creams.temo.tools.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {


    final ProjectBo2ProjectDto projectBo2ProjectDto = ProjectBo2ProjectDto.getInstance();
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
    @Transactional
    public String addProject(ProjectBo project) {
        String projectId = StringUtil.uuid();
        project.setPid(projectId);
        ProjectDto projectDto = projectBo2ProjectDto.convert(project);
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
    public List<ProjectBo> queryAllProjects() {
        List<ProjectDto> projectDtos = projectMapper.queryAllProject();
        List<ProjectBo> projectBos = (List<ProjectBo>) projectBo2ProjectDto.reverse().convertAll(projectDtos);
        return projectBos;
    }

    /**
     * 根据项目id查询所属环境
     *
     * @param projectId
     * @return
     */
    @Transactional
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
    @Transactional
    public PageInfo<ProjectBo> queryByName(Integer page, String name) {
        PageHelper.startPage(page, 10);
        List<ProjectDto> projects = projectMapper.queryProjectByName(name);
        List<ProjectBo> projectBos = (List<ProjectBo>) projectBo2ProjectDto.reverse().convertAll(projects);
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
    @Transactional
    public ProjectBo queryDetailById(String projectId) {
        ProjectDto projectDto = projectMapper.queryProjectById(projectId);
        ProjectBo projectBo = projectBo2ProjectDto.reverse().convert(projectDto);
        projectBo.setEnvs(envMapper.queryEnvByProjectId(projectId));
        return projectBo;
    }

    /**
     * 删除项目
     *
     * @param projectId
     * @return
     */
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
    @Transactional
    public String updateProjectById(String projectId, ProjectBo project) {
        String updateId = StringUtil.uuid();
        project.setPid(updateId);
        ProjectDto projectDto = projectBo2ProjectDto.convert(project);
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

        return updateId;
    }

}
