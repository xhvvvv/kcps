package com.wxfactory.kcps.frpfun.util;

import cn.hutool.setting.dialect.Props;

import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * @author xhvvvv
 * @time 2024/3/16
 */
public class Const {
    public static Properties ppties ;
    static {
        ppties = Props.getProp("remindText.properties", StandardCharsets.UTF_8).toProperties();
    }
    
}
