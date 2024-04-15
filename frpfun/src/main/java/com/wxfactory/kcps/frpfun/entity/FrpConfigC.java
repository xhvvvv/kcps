package com.wxfactory.kcps.frpfun.entity;


import com.wxfactory.kcps.frpfun.entity.frpconfigcs.FileFcc;
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.HttpFcc;
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.StcpFcc;
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.TcpFcc;
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.TcpmuxFcc;
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.UdpFcc;
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.XtcpFcc;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * 客户端的单个配置，可以是单个配置也可以是整个配置
 * @author  xhvvvv
 * @time    2024/3/16
 */
@Getter
@Setter
public class FrpConfigC extends FrpConfig{
    /**连接类型*/
    @Getter
    @Setter(value = AccessLevel.NONE)
    protected String type;
    /**本地设备ip*/
    private String localIP;
    private Integer localPort;

    private FrpConfigC pre;
    private FrpConfigC next;
    private static final Map<FrpcTypes,Class<? extends FrpConfigC>> mapperClass = new HashMap<>();
    static {
        mapperClass.put(FrpcTypes.HTTP, HttpFcc.class);
        mapperClass.put(FrpcTypes.STCP, StcpFcc.class);
        mapperClass.put(FrpcTypes.TCPMUX, TcpmuxFcc.class);
        mapperClass.put(FrpcTypes.UDP, UdpFcc.class);
        mapperClass.put(FrpcTypes.XTCP, XtcpFcc.class);
        mapperClass.put(FrpcTypes.TCP, TcpFcc.class);
    }
    
    public FrpConfigC(String host,int port) {
        super(host,port);
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
     public <T extends FrpConfigC> T newMyFccWithType(FrpcTypes type){
        T fcc = (T) newOneFccWithType(mapperClass.get(type));
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
            T fcc =  t.getDeclaredConstructor(String.class,int.class).newInstance(super.getHost(),super.getPort());
            return fcc;
        } catch (Exception e) {
            throw new NullPointerException("反射构造类失败："+t.getClass().getName());
        }
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
