package com.wxfactory.kcps.frpfun.entity.frpconfigcs;

import com.wxfactory.kcps.frpfun.entity.FrpConfigC;
import com.wxfactory.kcps.frpfun.entity.FrpcTypes;
import lombok.Getter;
import lombok.Setter;

/**
 * 端口复用
 * @author  xhvvvv
 * @time    2024/3/16
 */
@Getter
@Setter
public class TcpmuxFcc extends FrpConfigC {
 
    private String multiplexer;
    private String customDomains;
    private String localIP;
    private Integer localPort;
    
    
    public TcpmuxFcc(String host, int port) {
        super(host, port);
        this.type = FrpcTypes.TCPMUX.name();
    }
}
