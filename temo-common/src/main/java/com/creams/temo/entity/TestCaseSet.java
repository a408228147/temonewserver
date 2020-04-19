package com.creams.temo.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class TestCaseSet {

    @ApiModelProperty(value = "用例集id")
    private String setId;

    @ApiModelProperty("用例集名字")
    private String setName;

    @ApiModelProperty("用例集描述")
    private String setDesc;

    @ApiModelProperty("项目id")
    private String projectId;

    @ApiModelProperty("创建人")
    private String creator;

    @ApiModelProperty(value = "创建时间", hidden = true)
    private String createTime;

    @ApiModelProperty("用例集状态")
    private String setStatus;

    @ApiModelProperty("是否启用")
    private String valid;

    @ApiModelProperty("用例集合")
    private List<TestCase> testCase;


    @ApiModelProperty("前置脚本")
    private String setupScript;


    @ApiModelProperty("后置脚本")
    private String teardownScript;

}
