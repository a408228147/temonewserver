package com.creams.temo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExecuteTodayBo {
    @ApiModelProperty("今日执行用例数量")
    private Integer executeCaseTodayNum;

    @ApiModelProperty("成功数")
    private String successNum;

    @ApiModelProperty("失败数")
    private String falseNum;

    @ApiModelProperty("成功率")
    private String successRate;
}
