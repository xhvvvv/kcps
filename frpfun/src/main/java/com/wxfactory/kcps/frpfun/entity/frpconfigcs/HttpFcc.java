package com.wxfactory.kcps.frpfun.entity.frpconfigcs;

import com.wxfactory.kcps.frpfun.entity.FrpConfigC;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class HttpFcc extends FrpConfigC {
 
    private Set<String> customDomains;
    public HttpFcc(String host, int port) {
        super(host, port);
    }
}
