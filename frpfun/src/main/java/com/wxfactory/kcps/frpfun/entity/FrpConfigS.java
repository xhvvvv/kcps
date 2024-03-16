package com.wxfactory.kcps.frpfun.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FrpConfigS extends FrpConfig{
    private String kcpBindPort;
    private String quicBindPort;
    private String tcpmuxHTTPConnectPort ;
}
