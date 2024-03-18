package com.wxfactory.kcps.frpfun.frpBash;

import com.wxfactory.kcps.frpfun.entity.FrpConfig;

/**
 * 执行配置
 * @author  xhvvvv
 * @time    2024/3/18
 */
public interface ExcuteFrpFcc<T extends FrpConfig> {
    Process excute(T t );
}
