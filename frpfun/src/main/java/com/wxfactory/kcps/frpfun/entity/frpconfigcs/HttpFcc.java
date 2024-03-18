package com.wxfactory.kcps.frpfun.entity.frpconfigcs;

import com.wxfactory.kcps.frpfun.entity.FrpConfigC;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class HttpFcc extends FrpConfigC {
 
    private String customDomains;
    private String localPort;
    
    
    public HttpFcc(String host, int port) {
        super(host, port);
    }
}
