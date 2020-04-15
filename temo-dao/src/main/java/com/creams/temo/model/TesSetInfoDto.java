package com.creams.temo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TesSetInfoDto {
    @ApiModelProperty("测试用例集数量")
    private Integer testCaseSetNum;

    @ApiModelProperty("测试用例数量")
    private Integer testCaseNum;
}
