package com.wxfactory.kcps.frpfun.util;

import cn.hutool.core.lang.Snowflake;

/**
 * @author xhvvvv
 * @time 2024/5/27
 */
public class CommonUtil {
    
    public static String   generateId(){
        Snowflake snowflake = new Snowflake(1, 1);
        return String.valueOf(snowflake.nextId());
    }
}
