package com.creams.temo.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DatabaseBo {
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("数据库id")
    private String dbId;

    @ApiModelProperty("数据库名称")
    private String dbName;

    @ApiModelProperty("数据库类型:100-mysql,200-redis")
    private String dbType;

    @ApiModelProperty("主机Ip")
    private String host;

    @ApiModelProperty("断口号")
    private Integer port;

    @ApiModelProperty("用户名")
    private String user;

    @ApiModelProperty("密码")
    private String pwd;

    @ApiModelProperty("数据库名称")
    private String dbLibraryName;

    @ApiModelProperty("更新时间")
    private String updatetime;

    @ApiModelProperty("创建时间")
    private String createtime;
}
