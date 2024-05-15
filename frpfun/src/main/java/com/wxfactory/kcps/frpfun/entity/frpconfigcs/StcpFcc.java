package com.wxfactory.kcps.frpfun.entity.frpconfigcs;

import com.wxfactory.kcps.frpfun.entity.FrpConfigC;
import com.wxfactory.kcps.frpfun.entity.FrpcTypes;
import lombok.Getter;
import lombok.Setter;

/**
 * stcp安全tcp
 * @author  xhvvvv
 * @time    2024/3/16
 */
@Getter
@Setter
public class StcpFcc extends TcpFcc {
    private String side ;
    private String secretKey;
    private String serverName;
    public StcpFcc(String host, int port) {
        super(host, port);
        this.type = FrpcTypes.STCP.name();
    }
}
