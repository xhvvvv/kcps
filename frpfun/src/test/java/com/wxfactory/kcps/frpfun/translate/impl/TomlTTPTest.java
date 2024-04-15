package com.wxfactory.kcps.frpfun.translate.impl;

import com.wxfactory.kcps.frpfun.entity.FrpConfigC;
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.TcpFcc;
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.XtcpFcc;
import com.wxfactory.kcps.frpfun.frpBash.bash.ExcuteFrp;
import com.wxfactory.kcps.frpfun.frpBash.bash.ExcuteFrpFcc;
import com.wxfactory.kcps.frpfun.translate.ToTypeProcessor;
import org.junit.jupiter.api.Test;

import java.io.IOException;

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
    
        XtcpFcc xtcpFcc = tcpFcc.newMyFccWithType(XtcpFcc.class);
        xtcpFcc.setServerName("远程");
        xtcpFcc.setSecretKey("asdfasdf");
        xtcpFcc.setKeepTunnelOpen(false);
        
        String toml = toTypeProcessor.transfer(tcpFcc);
        System.out.println(toml);
    }
    
    /**
     * 
     */
    @Test
    public void xv_ICa0JKGN(){
        System.out.println("salkdjflaksdjflsd");   
    }
    
    /**
     * 
     */
    @Test
    public void xv_RIneXSdL() throws IOException {
        ProcessBuilder builder = new ProcessBuilder( );
        builder.command("D:\\Programmer\\EPOW_ALL\\Kotlin\\frpClient\\frpfun\\src\\test\\resources\\frp_0.52.3_windows_amd64\\frpc.exe",
                "-c" , "D:\\Programmer\\EPOW_ALL\\Kotlin\\frpClient\\frpfun\\src\\test\\resources\\frp_0.52.3_windows_amd64\\frpc.toml");
        // 启动进程
        Process process = builder.start();
        
        process.destroy();
        return;
    }
}
