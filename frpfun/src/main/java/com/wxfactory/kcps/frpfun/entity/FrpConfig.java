package com.wxfactory.kcps.frpfun.entity;

import java.io.Serializable;
import java.net.InetSocketAddress;

/**
 * 也许以后它们会有什么共性
 * @author  xhvvvv
 * @time    2024/3/16
 */
public class FrpConfig implements Serializable {
    private static final long serialVersionUID = -1522447679221326708L;
    /** 表示公网的地址 */
    private InetSocketAddress publicConnect;
    
    /** 认证方式 */
    private Authentication authentication;
}
