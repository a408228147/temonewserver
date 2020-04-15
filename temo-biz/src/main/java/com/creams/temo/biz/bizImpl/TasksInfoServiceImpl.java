package com.creams.temo.biz.bizImpl;


import com.creams.temo.biz.TasksInfoService;
import com.creams.temo.mapper.TasksInfoMapper;
import com.creams.temo.model.TaskInfoBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TasksInfoServiceImpl implements TasksInfoService {
    @Autowired
    TasksInfoMapper tasksInfoMapper;

    /**
     * 查询任务库信息
     *
     * @return
     */
    public TaskInfoBo queryTasksInfo() {
        TaskInfoBo taskInfoResponse = TaskInfoBo.builder()
                .taskNum(tasksInfoMapper.queryTaskNum())
                .taskIsTimingNum(tasksInfoMapper.queryTaskIsTimingNum()).build();
        return taskInfoResponse;
    }


    /**
     * 查询任务执行信息
     *
     * @return
     */
    public TaskInfoBo queryTasksExecuteInfo() {
        TaskInfoBo taskInfoResponse = TaskInfoBo.builder()
                .taskIsEndNum(tasksInfoMapper.queryTaskStatusIsEnd())
                .taskIsStartNum(tasksInfoMapper.queryTaskStatusIsStart()).build();
        return taskInfoResponse;
    }
}
