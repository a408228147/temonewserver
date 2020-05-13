package com.creams.temo.entity;


import lombok.Builder;
import lombok.Data;

/**
 * sql实体
 */
@Data
@Builder
public class SqlScript {

    // 执行脚本
    private String script;

    // 是否需要保存到map
    private Boolean saveParam;
}