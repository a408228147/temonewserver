package com.creams.temo.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.creams.temo.entity.Env;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Builder
@Data
@TableName(value = "project")
public class ProjectDto {

    @ApiModelProperty("项目主键")
    private Integer id;

    @ApiModelProperty("项目名称")
    @TableField(value = "pname")
    private String pname;

    @ApiModelProperty("项目id")
    @TableField(value = "pid")
    private String pid;

    @ApiModelProperty("环境")
    @TableField(exist = false)
    private List<Env> envs;

    @ApiModelProperty("创建时间")
    private Timestamp createTime;

    @ApiModelProperty("修改时间")
    private Timestamp updateTime;
}
