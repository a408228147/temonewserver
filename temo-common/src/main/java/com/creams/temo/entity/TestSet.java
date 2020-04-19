package com.creams.temo.entity;

import lombok.Builder;
import lombok.Data;

/**
 * 测试集
 */
@Data
@Builder
public class TestSet {

    private String setName;

    private String setId;

    private String envName;

    private String envId;
}
