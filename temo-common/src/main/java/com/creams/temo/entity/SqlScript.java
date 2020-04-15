package com.creams.temo.entity;


import lombok.Builder;
import lombok.Data;

/**
 * sql实体
 */
@Data
@Builder
public class SqlScript {

    private String script;

    private Boolean saveParam;
}