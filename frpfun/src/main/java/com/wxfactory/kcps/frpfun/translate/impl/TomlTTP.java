package com.wxfactory.kcps.frpfun.translate.impl;

import cn.hutool.core.util.StrUtil;
import com.wxfactory.kcps.frpfun.entity.Authentication;
import com.wxfactory.kcps.frpfun.entity.FrpConfigC;
import com.wxfactory.kcps.frpfun.entity.auths.TokenAuth;
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.HttpFcc;
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.StcpFcc;
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.TcpFcc;
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.TcpmuxFcc;
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.UdpFcc;
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
        //头部
        sb.append(handleHead(frpConfigC));
        
        
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
    
    @Override
    public String handleHead(FrpConfigC frpConfigC) {
        StringBuilder sb = new StringBuilder();
        sb.append("#").append((String)ppties.get("tofileHeader")).append("\n");
        //转根信息
        Authentication auth =  frpConfigC.getAuthentication();
        String host = frpConfigC.getHost();
        Integer port = frpConfigC.getPort();
        sb.append("serverAddr = ") 
                .append("\"")
                .append(host)
                .append("\"")
                .append("\n")
                .append("serverPort = ")
                .append(port)
                .append("\n");
        if (auth!=null){
            sb.append("auth.method = ")
                    .append("\"")
                    .append(auth.getMethod().name().toLowerCase())
                    .append("\"")
                    .append("\n");
            if (auth instanceof TokenAuth){
                sb.append("auth.token = ")
                        .append("\"")
                        .append(((TokenAuth) auth).getToken())
                        .append("\"")
                        .append("\n");
            }
          
        }
        
        sb.append("###########################################").append("\n");
        return sb.toString();
    }
    
    
    @Override
    public void handleSpecifyFcc(FrpConfigC frpConfigC , StringBuilder sb){
         if (frpConfigC instanceof XtcpFcc){
            XtcpFcc vvvv = ((XtcpFcc) frpConfigC);
             if (TcpFcc.W_S_C.equals(vvvv.getSide())){//客户端
                 sb.append("[[visitors]]").append("\n");
                 sb.append( "bindAddr = "       )       .append("\"")       .append( vvvv.getLocalIP())     .append("\"\n");
                 sb.append( "bindPort = "     )         .append("  ")       .append( vvvv.getLocalPort())   .append("  \n");
                 sb.append(" keepTunnelOpen = " )            .append( vvvv .getKeepTunnelOpen())     .append("\n") ;
                 sb.append(" serverName = "     )        .append("\"")           .append( vvvv .getServerName())         .append("\"\n") ;
                 
             }else{
                 sb.append("[[proxies]]").append("\n");
                 sb.append(" localIP = "        )        .append("\"")           .append( vvvv .getLocalIP())            .append("\"\n") ;
                 sb.append(" localPort = "      )        .append("  ")           .append( vvvv .getLocalPort())          .append("  \n") ;
             }
             sb.append( "secretKey  = "       )       .append("\"")       .append( vvvv.getSecretKey())     .append("\"\n");
        }else if (frpConfigC instanceof TcpmuxFcc) {
             
        }else if (frpConfigC instanceof StcpFcc) {
             StcpFcc vvvv = ((StcpFcc) frpConfigC);
             if (TcpFcc.W_S_C.equals(vvvv.getSide())){//客户端
                 sb.append("[[visitors]]").append("\n");
                 sb.append( "bindAddr = "       )       .append("\"")       .append( vvvv.getLocalIP())     .append("\"\n");
                 sb.append( "bindPort = "       )       .append("  ")         .append( vvvv.getLocalPort())   .append("  \n");
                 sb.append(" serverName = "     )        .append("\"")     .append( vvvv .getServerName())         .append("\"\n") ;
             }else{
                 sb.append("[[proxies]]").append("\n");
                 sb.append( "localIP = "       )       .append("\"")       .append( vvvv.getLocalIP())     .append("\"\n");
                 sb.append( "localPort = "     )       .append("  ")       .append( vvvv.getLocalPort())   .append("  \n");
             }
             sb.append( "secretKey  = "       )       .append("\"")       .append( vvvv.getSecretKey())     .append("\"\n");
        }else if (frpConfigC instanceof HttpFcc) {
             HttpFcc vvvv = ((HttpFcc) frpConfigC);
             sb.append("[[proxies]]").append("\n")
                     .append( "localPort = "     )       .append("  ")       .append( vvvv.getLocalPort())                           .append("  \n")
                     .append( "customDomains = "    )    .append("  ")       .append( vvvv.getCustomDomains().toArray().toString())  .append("  \n");
             
        }else if (frpConfigC instanceof UdpFcc) {
             UdpFcc vvvv = ((UdpFcc) frpConfigC);
             sb.append("[[proxies]]").append("\n")
                     .append( "localIP = "       )       .append("\"")       .append( vvvv.getLocalIP())     .append("\"\n")
                     .append( "localPort = "     )       .append("  ")       .append( vvvv.getLocalPort())   .append("  \n")
                     .append( "remotePort = "    )       .append("  ")       .append( vvvv.getRemotePort())  .append("  \n");
        }else if (frpConfigC instanceof TcpFcc) {// TCP转换
            TcpFcc vvvv = ((TcpFcc) frpConfigC);
            sb.append("[[proxies]]").append("\n")
                    .append( "localIP = "       )       .append("\"")       .append( vvvv.getLocalIP())     .append("\"\n")
                    .append( "localPort = "     )       .append("  ")       .append( vvvv.getLocalPort())   .append("  \n")
                    .append( "remotePort = "    )       .append("  ")       .append( vvvv.getRemotePort())  .append("  \n");
        }
        
        sb.append("name = ")   .append("\"")       .append( frpConfigC .getName())    .append("\"\n")
          .append("type = ")   .append("\"")       .append( frpConfigC .getType().toLowerCase())    .append("\"\n");
    }
    
}

