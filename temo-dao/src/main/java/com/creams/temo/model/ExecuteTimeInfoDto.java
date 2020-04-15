package com.creams.temo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class ExecuteTimeInfoDto {

    @ApiModelProperty("平台运行时间")
    private Map<String, Integer> executeTime;

    @ApiModelProperty("正在执行任务数量")
    private Integer executeTaskNumNow;
}
