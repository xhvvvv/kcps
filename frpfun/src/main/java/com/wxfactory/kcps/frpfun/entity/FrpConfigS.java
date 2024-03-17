package com.wxfactory.kcps.frpfun.entity;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.InetSocketAddress;

@Getter
@Setter
public class FrpConfigS extends FrpConfig{
    private String kcpBindPort;
    private String quicBindPort;
    private String tcpmuxHTTPConnectPort ;

    public FrpConfigS(@NotNull InetSocketAddress publicConnect) {
        super(publicConnect);
    }

    public FrpConfigS(@NotNull InetSocketAddress publicConnect, @Nullable Authentication authentication) {
        super(publicConnect, authentication);
    }
}
