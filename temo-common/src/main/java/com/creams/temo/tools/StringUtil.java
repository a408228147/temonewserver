package com.creams.temo.tools;

import java.util.UUID;

public class StringUtil {
    /**
     * 生成64位uuid
     * @return
     */
    public static String uuid(){
        return UUID.randomUUID().toString();
    }

    public static boolean isEmptyOrNull(String str){
        return "".equals(str) || str == null;
    }

    public static String log(String logLevel,String logs){
        return String.format("【 %s 】【 %s 】-- %s\n",DateUtil.getCurrentTime(),logLevel,logs);
    }
}
