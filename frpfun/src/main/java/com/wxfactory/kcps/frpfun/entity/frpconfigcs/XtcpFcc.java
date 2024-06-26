package com.wxfactory.kcps.frpfun.entity.frpconfigcs;

import com.wxfactory.kcps.frpfun.entity.FrpcTypes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class XtcpFcc extends TcpFcc {
    private static final long serialVersionUID = 4744989927558045363L;
    private String side ;
    private String serverName;
    private String secretKey;
    private Boolean keepTunnelOpen;
    
    public XtcpFcc(String host, int port) {
        super(host, port);
        this.type = FrpcTypes.XTCP.name();
    }
}
