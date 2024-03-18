package com.wxfactory.kcps.frpfun.entity.frpconfigcs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class XtcpFcc extends TcpFcc {
  
    private String serverName;
    private String secretKey;
    private String bindAddr;
    private String bindPort;
    private Boolean keepTunnelOpen;
    
    public XtcpFcc(String host, int port) {
        super(host, port);
    }
}
