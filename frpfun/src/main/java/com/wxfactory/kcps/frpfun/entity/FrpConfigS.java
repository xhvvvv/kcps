package com.wxfactory.kcps.frpfun.entity;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.InetSocketAddress;

@Getter
@Setter
public class FrpConfigS extends FrpConfig{
    private Integer kcpBindPort;
    private Integer quicBindPort;
    private Integer tcpmuxHTTPConnectPort ;
    
    
    public FrpConfigS(@NotNull String host, @NotNull Integer port) {
        super(host, port);
    }
}
