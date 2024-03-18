package com.wxfactory.kcps.frpfun.entity.frpconfigcs;

import com.wxfactory.kcps.frpfun.entity.FrpConfigC;
import lombok.Getter;
import lombok.Setter;

/**
 * stcp安全tcp
 * @author  xhvvvv
 * @time    2024/3/16
 */
@Getter
@Setter
public class StcpFcc extends FrpConfigC {
  
    private String localIP;
    private String localPort;
    private String secretKey;
    
    
    public StcpFcc(String host, int port) {
        super(host, port);
    }
}
