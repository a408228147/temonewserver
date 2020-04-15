package com.creams.temo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TaskInfoDto {
    @ApiModelProperty("任务数量")
    private Integer taskNum;

    @ApiModelProperty("任务执行中数量")
    private Integer taskIsStartNum;

    @ApiModelProperty("任务执行完毕数量")
    private Integer taskIsEndNum;

    @ApiModelProperty("开启定时任务数量")
    private Integer taskIsTimingNum;
}
