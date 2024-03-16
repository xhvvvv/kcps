package com.wxfactory.kcps.frpfun.entity.frpconfigcs;

import com.wxfactory.kcps.frpfun.entity.FrpConfigC;
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
   
    private String localIP;
    private String localPort;
    private String remotePort;
}
