package com.creams.temo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskDto {

    @ApiModelProperty("任务id")
    private String taskId;

    @ApiModelProperty("任务名称")
    private String taskName;

    @ApiModelProperty("任务描述")
    private String taskDesc;

    @ApiModelProperty("是否定时，0为否，1为是")
    private String isTiming;

    @ApiModelProperty("是否并行，0为否，1为是")
    private String isParallel;

    @ApiModelProperty("钉钉开关")
    private Integer isDing;

    @ApiModelProperty("钉钉id")
    private String dingId;

    @ApiModelProperty("创建人")
    private String creator;

    @ApiModelProperty("轮询次数")
    private Integer times;

    @ApiModelProperty("用例集")
    private String testSets;
}
