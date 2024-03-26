package com.wxfactory.kcps.frpfun.entity;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.net.InetSocketAddress;

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
    /** 表示公网的地址 */
    @NotNull
    protected InetSocketAddress publicConnect;
    
    /** 认证方式 */
    @Nullable
    protected Authentication authentication;

    public FrpConfig(@NotNull InetSocketAddress publicConnect) {
        this.publicConnect = publicConnect;
    }

    public FrpConfig(String host,int port) {
        this.publicConnect = new InetSocketAddress(host,port);
    }

    public FrpConfig(@NotNull InetSocketAddress publicConnect, @Nullable Authentication authentication) {
        this.publicConnect = publicConnect;
        this.authentication = authentication;
    }
}
