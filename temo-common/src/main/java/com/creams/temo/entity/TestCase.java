package com.creams.temo.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class TestCase {

    @ApiModelProperty("用例id")
    private String caseId;


    @ApiModelProperty("用例描述")
    private String caseDesc;

    @ApiModelProperty("环境id")
    private String envId;

    @ApiModelProperty("接口地址")
    private String url;

    @ApiModelProperty("请求方式")
    private String method;

    @ApiModelProperty("请求头")
    private String header;


    @ApiModelProperty("请求cookies")
    private String cookies;

    @ApiModelProperty("请求参数")
    private String param;

    @ApiModelProperty("请求体")
    private String body;

    @ApiModelProperty("请求文件url")
    private String file;

    @ApiModelProperty("集合id")
    private String setId;

    @ApiModelProperty("sql脚本")
    private String sqlScript;

    @ApiModelProperty("数据库id")
    private String dbId;

    @ApiModelProperty("用例类型")
    private String caseType;

    @ApiModelProperty("全局变量")
    private String globalVariables;

    @ApiModelProperty("全局请求头")
    private String globalHeaders;

    @ApiModelProperty("全局Cookies")
    private String globalCookies;

    @ApiModelProperty("延迟时间")
    private String delayTime;

    @ApiModelProperty("JSON断言")
    private String  jsonAssert;

    @ApiModelProperty("排序")
    private Integer sorting;

    @ApiModelProperty("请求正文类型")
    private String contentType;

    @ApiModelProperty("是否执行")
    private Integer isRun;

    private List<Verify> verify;

    private List<Saves> saves;
}
