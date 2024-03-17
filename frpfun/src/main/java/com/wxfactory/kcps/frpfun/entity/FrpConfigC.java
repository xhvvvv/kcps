package com.wxfactory.kcps.frpfun.entity;


import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.InetSocketAddress;

/**
 * 客户端的单个配置，可以是单个配置也可以是整个配置
 * @author  xhvvvv
 * @time    2024/3/16
 */
@Getter
@Setter
public class FrpConfigC extends FrpConfig{
    /**名称*/
    protected String name;
    /**连接类型*/
    protected String type;
    
    
    private FrpConfigC pre;
    private FrpConfigC next;

    /**
     * 在本配置添加一个节点
     * @author xhvvvv
     * @date 2024/3/17
     * @param t
     * @return
     */
    public <T extends FrpConfigC> T newMyFccWithType(Class<T> t){
        
    }
    
    /**
     * 新增一个独立的配置节点，服务器配置是一样的。
     * @author xhvvvv
     * @date 2024/3/17
     * @param t
     * @return
     */
    public <T extends FrpConfigC> T newOneFccWithType(Class<T> t){
        
    }
    
    public FrpConfigC(@NotNull InetSocketAddress publicConnect) {
        super(publicConnect);
    }

    public FrpConfigC(@NotNull InetSocketAddress publicConnect, @Nullable Authentication authentication) {
        super(publicConnect, authentication);
    }

    @Override
    public InetSocketAddress getPublicConnect(){
        if (super.getPublicConnect() != null )
            return super.getPublicConnect();
        else
            return pre.getPublicConnect();
    }
    
    @Override
    public Authentication getAuthentication(){
        if (super.getAuthentication() != null )
            return super.getAuthentication();
        else
            return pre.getAuthentication();
    }
    /**
     * 获取根节点
     * @author xhvvvv
     * @date 2024/3/16
     * @param
     * @return
     */
    public FrpConfigC getRootFCC(){
        if (pre == null ){
            return pre;
        }else{
            return pre.getRootFCC();
        }
    }
    
}
