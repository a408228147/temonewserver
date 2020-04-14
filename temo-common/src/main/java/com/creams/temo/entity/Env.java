package com.creams.temo.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Env {
    @ApiModelProperty("项目主键")
    private Integer id;

    @ApiModelProperty("环境id,不必传")
    private String envId;

    @ApiModelProperty("环境名称")
    private String envName;

    @ApiModelProperty("域名")
    private String host;

    @ApiModelProperty("端口号")
    private Integer port;

    @ApiModelProperty("项目id,不必传")
    private String projectId;
}
