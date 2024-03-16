package com.wxfactory.kcps.frpfun.translate;

import com.wxfactory.kcps.frpfun.entity.FrpConfig;

/**
 * 此接口用于将面板配置转换成各种不同格式的配置文件
 * @author  xhvvvv
 * @time    2024/3/16
 */
public interface ToTypeProcessor<T extends FrpConfig> {
    /**
     * 转换对象为配置文件字符串
     * @author xhvvvv
     * @date 2024/3/16
     * @param t 目前我想到的就json、toml、ini
     * @return
     */
    String transfer(T t);
}
