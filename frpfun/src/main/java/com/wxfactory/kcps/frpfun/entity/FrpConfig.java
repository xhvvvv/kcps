package com.wxfactory.kcps.frpfun.entity;

import cn.hutool.core.lang.Snowflake;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * 也许以后它们会有什么共性
 * @author  xhvvvv
 * @time    2024/3/16
 */
@Setter
@Getter
public abstract class FrpConfig implements Serializable {
    private static final long serialVersionUID = -1522447679221326708L;
    /**名称*/
    protected String name;
    
    /**标识Id */
    protected String id;
    /** 表示公网的地址 */
    protected String host;
    protected Integer port;
    /** 是否自启--这个状态不好放，暂且放在这个类中 */
    protected Boolean enabled =false;
    /** 认证方式 */
    @Nullable
    protected Authentication authentication;
    
  
    public FrpConfig(@NotNull String host,@NotNull Integer port) {
        this.host = host;
        this.port = port;
    }

    public FrpConfig(@NotNull String host,@NotNull Integer port, @Nullable Authentication authentication) {
        this.host = host;
        this.port = port;
        this.authentication = authentication;
    }
}
