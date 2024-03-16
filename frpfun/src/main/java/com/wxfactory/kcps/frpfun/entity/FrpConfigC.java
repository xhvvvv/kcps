package com.wxfactory.kcps.frpfun.entity;


import lombok.Getter;
import lombok.Setter;

/**
 * 客户端的配置
 * @author  xhvvvv
 * @time    2024/3/16
 */
@Getter
@Setter
public class FrpConfigC extends FrpConfig{
    /**名称*/
    private String name;
    /**连接类型*/
    private String type;
}
