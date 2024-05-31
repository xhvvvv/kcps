package com.wxfactory.kcps.frpfun.translate.impl;

import cn.hutool.core.util.StrUtil;
import com.wxfactory.kcps.frpfun.entity.Authentication;
import com.wxfactory.kcps.frpfun.entity.FrpConfigC;
import com.wxfactory.kcps.frpfun.entity.FrpConfigS;
import com.wxfactory.kcps.frpfun.entity.auths.MethodType;
import com.wxfactory.kcps.frpfun.entity.auths.TokenAuth;
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.HttpFcc;
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.StcpFcc;
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.TcpFcc;
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.TcpmuxFcc;
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.UdpFcc;
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.XtcpFcc;
import com.wxfactory.kcps.frpfun.translate.ToTypeProcessor;

import static com.wxfactory.kcps.frpfun.util.Const.ppties;

/**
 * 转化为toml配置文件
 * @author xhvvvv
 * @time 2024/3/16
 */
public class TomlSTTP implements ToTypeProcessor<FrpConfigS> {
    
    @Override
    public String  transfer(FrpConfigS frp) {
        StringBuilder sb = new StringBuilder();
        //头部
        sb.append(handleHead(frp));
        handleSpecifyFcc(frp,sb);
        return sb.toString();
    }
    
    @Override
    public String handleHead(FrpConfigS frp) {
        StringBuilder sb = new StringBuilder();
        sb.append("#").append((String)ppties.get("tofileHeader")).append("\n");
        //转根信息
        Authentication auth =  frp.getAuthentication();
        String host = frp.getHost();
        Integer port = frp.getPort();
        if (StrUtil.isEmpty(host)){
            sb.append("bindAddr = ")   .append("\"")       .append( "0.0.0.0" )    .append("\"\n");
        }else{
            sb.append("bindAddr = ")   .append("\"")       .append( host )    .append("\"\n");
        }
        
        sb.append("bindPort = ")        .append( port )    .append("\n");
        if(frp.getKcpBindPort()!=null){
            sb.append("kcpBindPort = ").append( frp.getKcpBindPort() )  .append("\n");
        }
       
        if(frp.getQuicBindPort()!=null){
            sb.append("quicBindPort = ").append( frp.getQuicBindPort() )  .append("\n");
        }
        
        if (frp.getTcpmuxHTTPConnectPort()!=null){
            sb.append("tcpmuxHTTPConnectPort = ").append( frp.getTcpmuxHTTPConnectPort() )  .append("\n");
        }
        if (frp.getVhostHTTPPort()!=null){
            sb.append("vhostHTTPPort = ").append( frp.getVhostHTTPPort() )  .append("\n");
        }
        if (frp.getVhostHTTPSPort()!=null){
            sb.append("vhostHTTPSPort = ").append( frp.getVhostHTTPSPort() )  .append("\n");
        }
        if (frp.getVhostHTTPTimeout()!=null){
            sb.append("vhostHTTPTimeout = ").append( frp.getVhostHTTPTimeout() )  .append("\n");
        }
        
        
        if (frp.getTcpmuxPassthrough()!=null){
            sb.append("tcpmuxPassthrough = ").append( frp.getTcpmuxPassthrough() )  .append("\n");
        }
        
        if (frp.getDetailedErrorsToClient()!=null){
            sb.append("detailedErrorsToClient = ").append( frp.getDetailedErrorsToClient() )  .append("\n");
        }
        
        if (frp.getMaxPortsPerClient()!=null){
            sb.append("maxPortsPerClient = ").append( frp.getMaxPortsPerClient() )  .append("\n");
        }
        
        
        if (frp.getAuthentication()!=null){
            if (frp.getAuthentication() instanceof TokenAuth){
                sb.append("auth.method = ")   .append("\"")       .append(MethodType.TOKEN.name().toLowerCase() )    .append("\"\n");
                sb.append("auth.token = ")   .append("\"")       .append( ((TokenAuth) frp.getAuthentication()).getToken() )    .append("\"\n");
            }
        }
        
        sb.append("###########################################").append("\n");
        return sb.toString();
    }
    
    
    @Override
    public void handleSpecifyFcc(FrpConfigS frp , StringBuilder sb) {
        
    }
    
}

