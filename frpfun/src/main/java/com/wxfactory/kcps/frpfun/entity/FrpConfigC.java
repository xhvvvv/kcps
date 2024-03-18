package com.wxfactory.kcps.frpfun.entity;


import lombok.Getter;
import lombok.Setter;

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
   
    public FrpConfigC(String host,int port) {
        super(new InetSocketAddress(host,port));
    }
    /**
     * 在本配置添加一个节点
     * @author xhvvvv
     * @date 2024/3/17
     * @param t
     * @return
     */
    public <T extends FrpConfigC> T newMyFccWithType(Class<T> t){
        T fcc = newOneFccWithType(t);
        fcc.setPre(this);
        this.setNext(fcc);
        return fcc;
    }
    
    /**
     * 新增一个独立的配置节点，服务器配置是一样的。
     * @author xhvvvv
     * @date 2024/3/17
     * @param t
     * @return
     */
    public <T extends FrpConfigC> T newOneFccWithType(Class<T> t){
        try {
            T fcc =  t.getDeclaredConstructor(String.class,int.class).newInstance(super.publicConnect.getHostString(),super.getPublicConnect().getPort());
            return fcc;
        } catch (Exception e) {
            throw new NullPointerException("反射构造类失败："+t.getClass().getName());
        }
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
        else if (pre !=null)
            return pre.getAuthentication();
        else return null;
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