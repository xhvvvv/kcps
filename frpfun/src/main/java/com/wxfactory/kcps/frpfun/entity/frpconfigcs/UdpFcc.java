package com.wxfactory.kcps.frpfun.entity.frpconfigcs;

import com.wxfactory.kcps.frpfun.entity.FrpConfigC;
import com.wxfactory.kcps.frpfun.entity.FrpcTypes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UdpFcc extends FrpConfigC {
   
    private String localIP;
    private Integer localPort;
    private Integer remotePort;
    
    
    public UdpFcc(String host, int port) {
        super(host, port);
        this.type = FrpcTypes.UDP.name();
    }
}
