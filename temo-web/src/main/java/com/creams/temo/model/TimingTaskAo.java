package com.creams.temo.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.creams.temo.entity.TestSet;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TimingTaskAo {


    @ApiModelProperty("任务id")
    private String taskId;

    @ApiModelProperty("任务名称")
    private String taskName;

    @ApiModelProperty("任务描述")
    private String taskDesc;

    @ApiModelProperty("创建人")
    private String creator;

    @ApiModelProperty("是否并行，0为否，1为是")
    private String isParallel;

    @ApiModelProperty("cron表达式")
    private String cron;

    @ApiModelProperty("轮询次数")
    private Integer times;

    @ApiModelProperty("开启/关闭")
    @TableField(value = "is_open")
    private Integer isOpen;

    @ApiModelProperty("邮件开关")
    private Integer isMail;

    @ApiModelProperty("邮件")
    private String mail;

    @ApiModelProperty("钉钉开关")
    private Integer isDing;

    @ApiModelProperty("钉钉id")
    private String dingId;

    @ApiModelProperty("用例集")
    private String testSets;

    @ApiModelProperty("用例列表")
    private List<TestSet> testSetList;

    @ApiModelProperty("创建时间")
    private String createTime;

    @ApiModelProperty("修改时间")
    private String updateTime;


}
