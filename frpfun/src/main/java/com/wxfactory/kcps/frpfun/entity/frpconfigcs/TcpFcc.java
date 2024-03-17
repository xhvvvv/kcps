package com.wxfactory.kcps.frpfun.entity.frpconfigcs;

import com.wxfactory.kcps.frpfun.entity.Authentication;
import com.wxfactory.kcps.frpfun.entity.FrpConfigC;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.InetSocketAddress;

/**
 * 普通的tcp端口穿透
 * @author  xhvvvv
 * @time    2024/3/16
 */
@Getter
@Setter
public class TcpFcc extends FrpConfigC {
    /**本地设备ip*/
    private String localIP;
    private String localPort;
    
    /**映射到frps的某一个端口*/
    private String remotePort;


    public TcpFcc(@NotNull InetSocketAddress publicConnect) {
        super(publicConnect);
    }

    public TcpFcc(@NotNull InetSocketAddress publicConnect, @Nullable Authentication authentication) {
        super(publicConnect, authentication);
    }
}
