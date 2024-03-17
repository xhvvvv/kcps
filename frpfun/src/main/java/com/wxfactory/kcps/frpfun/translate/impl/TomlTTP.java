package com.wxfactory.kcps.frpfun.translate.impl;

import com.wxfactory.kcps.frpfun.entity.Authentication;
import com.wxfactory.kcps.frpfun.entity.FrpConfigC;
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.TcpFcc;
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
    public String transfer(FrpConfigC frpConfigC) {
        StringBuilder sb = new StringBuilder((String)ppties.get("tofileHeader"));
    
        //转根信息
        Authentication authentication =  frpConfigC.getAuthentication();
        InetSocketAddress inetSocketAddress = frpConfigC.getPublicConnect();
        sb.append("serverAddr = ").append(inetSocketAddress.getHostName())
                .append("serverPort = ").append(inetSocketAddress.getPort());
        
        if (authentication!=null){
            // Xhvvvv_Sign_TODO xhvvvv 2024/3/16 默认一周内完成  
            sb.append("auth.method = ").append("");
        }
        
        sb.append("###########################################");
        
        FrpConfigC temPre  = frpConfigC;
        FrpConfigC temNext = frpConfigC;
        
        //向前遍历
        do{
            handleSpecifyFcc(temPre , sb);
        } while ((temPre = frpConfigC.getPre() )!=null);
        
        
        //向后遍历
        while ((temNext = frpConfigC.getNext() )!=null){
            handleSpecifyFcc(temPre , sb);
        }
        return sb.toString();
    }
    
    
    
    private void handleSpecifyFcc(FrpConfigC frpConfigC , StringBuilder sb){
        
        if (frpConfigC instanceof TcpFcc) {
            TcpFcc vvvv = ((TcpFcc) frpConfigC);
            sb.append("[[proxies]]")
                    .append("localIP = ")   .append("\"")       .append( vvvv .getLocalIP())    .append("\"")
                    .append("localPort = ") .append("\"")       .append( vvvv.getLocalPort())   .append("\"")
                    .append("remotePort = ").append("\"")       .append( vvvv.getRemotePort())  .append("\"");
        }
        sb.append("name = ")   .append("\"")       .append( frpConfigC .getName())    .append("\"")
          .append("type = ")   .append("\"")       .append( frpConfigC .getType())    .append("\"");
    }
    
}

