package com.wxfactory.kcps.frpfun.entity.frpconfigcs;

import com.wxfactory.kcps.frpfun.entity.FrpConfigC;
import com.wxfactory.kcps.frpfun.entity.FrpcTypes;
import lombok.Getter;
import lombok.Setter;

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
    private Integer localPort;
    
    /**映射到frps的某一个端口*/
    private Integer remotePort;
    
    
    public TcpFcc(String host, int port) {
        super(host, port);
        this.type = FrpcTypes.TCP.name();
    }
}
