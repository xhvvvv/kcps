package com.wxfactory.kcps.frpfun.translate.impl;

import com.wxfactory.kcps.frpfun.entity.FrpConfigC;
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.TcpFcc;
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.XtcpFcc;
import com.wxfactory.kcps.frpfun.translate.ToTypeProcessor;
import org.junit.jupiter.api.Test;

/**
 * @author xhvvvv
 * @time 2024/3/16
 */
public class TomlTTPTest {
    /**
     * 
     */
    @Test
    public void xv_IJEUN2tP(){
        ToTypeProcessor<FrpConfigC> toTypeProcessor = new TomlTTP();
        TcpFcc tcpFcc = new TcpFcc("Nolocalhost",8080);
        tcpFcc.setLocalIP("localhost");
        tcpFcc.setLocalPort(9999);
        tcpFcc.setName("测试");
        tcpFcc.setType("tcp");
    
        XtcpFcc xtcpFcc = tcpFcc.newMyFccWithType(XtcpFcc.class);
        xtcpFcc.setServerName("远程");
        xtcpFcc.setSecretKey("asdfasdf");
        xtcpFcc.setBindAddr("localhost");
        xtcpFcc.setBindPort("908");
        xtcpFcc.setKeepTunnelOpen(false);
        
        String toml = toTypeProcessor.transfer(tcpFcc);
        System.out.println(toml);
    }
    
}
