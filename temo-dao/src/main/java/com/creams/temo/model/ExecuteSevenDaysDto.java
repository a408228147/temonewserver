package com.creams.temo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExecuteSevenDaysDto {

    @ApiModelProperty("成功用例数量")
    private Integer successNum;

    @ApiModelProperty("失败用例数量")
    private Integer falseNum;

    @ApiModelProperty("成功率")
    private String successRate;


}
