package com.wxfactory.kcps.frpfun.entity.frpconfigcs;

import com.wxfactory.kcps.frpfun.entity.FrpConfigC;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UdpFcc extends FrpConfigC {
   
    private String localIP;
    private String localPort;
    private String remotePort;
    
    
    public UdpFcc(String host, int port) {
        super(host, port);
    }
}
