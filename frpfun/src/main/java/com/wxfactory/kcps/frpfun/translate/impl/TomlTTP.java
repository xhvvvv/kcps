package com.wxfactory.kcps.frpfun.translate.impl;

import cn.hutool.core.util.StrUtil;
import com.wxfactory.kcps.frpfun.entity.Authentication;
import com.wxfactory.kcps.frpfun.entity.FrpConfigC;
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.TcpFcc;
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.XtcpFcc;
import com.wxfactory.kcps.frpfun.translate.ToTypeProcessor;

import java.net.InetSocketAddress;

import static com.wxfactory.kcps.frpfun.util.Const.ppties;

/**
 * 转化为toml配置文件
 * @author xhvvvv
 * @time 2024/3/16
 */
public class TomlTTP implements ToTypeProcessor<FrpConfigC> {
    
    @Override
    public String  transfer(FrpConfigC frpConfigC) {
        StringBuilder sb = new StringBuilder();
        sb.append("#").append((String)ppties.get("tofileHeader")).append("\n");
        
        //转根信息
        Authentication authentication =  frpConfigC.getAuthentication();
        String host = frpConfigC.getHost();
        Integer port = frpConfigC.getPort();
        sb.append("serverAddr = ").append(host).append("\n")
                .append("serverPort = ").append(port).append("\n");
        
        if (authentication!=null){
            // Xhvvvv_Sign_TODO xhvvvv 2024/3/16 默认一周内完成  
            sb.append("auth.method = ").append("").append("\n");
        }
        
        sb.append("###########################################").append("\n");
        
        FrpConfigC temPre  = frpConfigC;
        //向前遍历
        do{
            handleSpecifyFcc(temPre , sb);
        } while ((temPre = temPre.getPre() )!=null);
    
        FrpConfigC temNext = frpConfigC;
        //向后遍历
        while ((temNext = temNext.getNext() )!=null){
            handleSpecifyFcc(temNext , sb);
        }
        return sb.toString();
    }
    
    
    
    private void handleSpecifyFcc(FrpConfigC frpConfigC , StringBuilder sb){
         if (frpConfigC instanceof XtcpFcc){
            XtcpFcc vvvv = ((XtcpFcc) frpConfigC);
            sb.append("[[proxies]]").append("\n")
                    .append("secretKey = ")   .append("\"")       .append( vvvv .getSecretKey())    .append("\"\n");
        
            if (StrUtil.isNotEmpty(vvvv .getServerName()))  sb.append("serverName = ")      .append("\"")       .append( vvvv .getServerName())         .append("\"\n");
            if (StrUtil.isNotEmpty(vvvv .getBindAddr()))    sb.append("bindAddr = ")        .append("\"")       .append( vvvv .getBindAddr())           .append("\"\n");
            if (StrUtil.isNotEmpty(vvvv .getBindPort()))    sb.append("bindPort = ")        .append("\"")       .append( vvvv .getBindPort())           .append("\"\n");
            if (vvvv .getKeepTunnelOpen() != null )         sb.append("keepTunnelOpen = ")  .append("\"")       .append( vvvv .getKeepTunnelOpen())     .append("\"\n");
        
            if (StrUtil.isNotEmpty(vvvv .getLocalIP()))     sb.append("localIP = ")         .append("\"")       .append( vvvv .getLocalIP())            .append("\"\n");
            if (vvvv .getLocalPort() !=null)                sb.append("localPort = ")       .append("\"")       .append( vvvv .getLocalPort())          .append("\"\n");
        
        }else if (frpConfigC instanceof TcpFcc) {// TCP转换
            TcpFcc vvvv = ((TcpFcc) frpConfigC);
            sb.append("[[proxies]]").append("\n")
                    .append("localIP = ")   .append("\"")       .append( vvvv .getLocalIP())    .append("\"\n")
                    .append("localPort = ") .append("\"")       .append( vvvv.getLocalPort())   .append("\"\n")
                    .append("remotePort = ").append("\"")       .append( vvvv.getRemotePort())  .append("\"\n");
        }
        
        sb.append("name = ")   .append("\"")       .append( frpConfigC .getName())    .append("\"\n")
          .append("type = ")   .append("\"")       .append( frpConfigC .getType())    .append("\"\n");
    }
    
}

