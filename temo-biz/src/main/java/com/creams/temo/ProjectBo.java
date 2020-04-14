package com.creams.temo;

import com.creams.temo.entity.Env;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
public class ProjectBo {
    @ApiModelProperty("项目主键")
    private Integer id;

    @ApiModelProperty("项目名称")
    private String pname;

    @ApiModelProperty("项目id")
    private String pid;

    @ApiModelProperty("环境")
    private List<Env> envs;

    @ApiModelProperty("创建时间")
    private Timestamp createTime;

    @ApiModelProperty("修改时间")
    private Timestamp updateTime;


}
