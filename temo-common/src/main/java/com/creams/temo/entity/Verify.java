package com.creams.temo.entity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Verify {

    @ApiModelProperty("断言id")
    private String verifyId;

    @ApiModelProperty("断言类型（正则 or jsonpath)")
    private String verifyType;

    @ApiModelProperty("jsonpath表达式")
    private String jexpression;

    @ApiModelProperty("预期结果")
    private String expect;

    @ApiModelProperty("断言关系")
    private String relationShip;

    @ApiModelProperty("正则表达式")
    private String rexpression;

    @ApiModelProperty("用例id")
    private String caseId;


}
