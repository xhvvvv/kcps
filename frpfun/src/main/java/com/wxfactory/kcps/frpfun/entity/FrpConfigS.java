package com.wxfactory.kcps.frpfun.entity;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.InetSocketAddress;

@Getter
@Setter
public class FrpConfigS extends FrpConfig{
    private Integer kcpBindPort;
    private Integer quicBindPort;
//    private Transport transport ;
    
//    # Specify which address proxy will listen for, default value is same with bindAddr
//    # proxyBindAddr = "127.0.0.1"
//    
//    # quic protocol options
//    # transport.quic.keepalivePeriod = 10
//    # transport.quic.maxIdleTimeout = 30
//    # transport.quic.maxIncomingStreams = 100000
//    
//    # Heartbeat configure, it's not recommended to modify the default value
//    # The default value of heartbeatTimeout is 90. Set negative value to disable it.
//    # transport.heartbeatTimeout = 90
//    
//    # Pool count in each proxy will keep no more than maxPoolCount.
//    transport.maxPoolCount = 5
//    
//    # If tcp stream multiplexing is used, default is true
//    # transport.tcpMux = true
//    
//    # Specify keep alive interval for tcp mux.
//    # only valid if tcpMux is true.
//    # transport.tcpMuxKeepaliveInterval = 30
//    
//    # tcpKeepalive specifies the interval between keep-alive probes for an active network connection between frpc and frps.
//    # If negative, keep-alive probes are disabled.
//    # transport.tcpKeepalive = 7200
//    
//    # transport.tls.force specifies whether to only accept TLS-encrypted connections. By default, the value is false.
//    transport.tls.force = false
//    
//    # transport.tls.certFile = "server.crt"
//    # transport.tls.keyFile = "server.key"
//    # transport.tls.trustedCaFile = "ca.crt"
    
    /**
     *  http协议请求监听，即http代理服务，可以在这个端口监听到的请求转到对应的frp客户端上去
     *  服务器
     *  [common]
     *  bind_port = 7000
     *  vhost_http_port = 8080
     *  
     *  提供服务的端A
     *  [web]
     *   type = http
     *   local_port = 80
     *   custom_domains = www.example.com
     *   
     *   www.example.com:8080 可访问端A,不过你的域名得指向frp服务器
     */
    private Integer vhostHTTPPort   ;
    private Integer vhostHTTPSPort ;
    private Integer vhostHTTPTimeout = 60 ;
    
    /***
     * 用tcp多路复用基础和http隧道来实现 tcpmux和httpConnect 两个技术 来实现一个TCP复用端口
     * 
     *  tcpmuxHTTPConnectPort = 5002
     *  
     *  
     *  [[proxies]]
     *  name = "ssh1"
     *  type = "tcpmux"
     *  multiplexer = "httpconnect"
     *  customDomains = ["machine-a.example.com"]
     *  localIP = "127.0.0.1"
     *  localPort = 22
     *  Deploy another frpc on the internal machine B with the following configuration:
     *  serverAddr = "x.x.x.x"
     *  serverPort = 7000
     *  
     *  [[proxies]]
     *  name = "ssh2"
     *  type = "tcpmux"
     *  multiplexer = "httpconnect"
     *  customDomains = ["machine-b.example.com"]
     *  localIP = "127.0.0.1"
     *  localPort = 22
     *  
     *  通过 machine-b.example.com:5002 可以连接到ssh2服务
     */
    private Integer tcpmuxHTTPConnectPort = 0 ;
    
    
            
    /**
     * * frp可能对某些请求进行协议转换，导致目的端收到的协议不匹配，开启表示不转换任何协议
     */
    private Boolean tcpmuxPassthrough = false ;
            
    /**
     * dashboard
     */
//    private WebServer webServer ;  enablePrometheus = true
            
    /**
     * 
     */
//    private Log log
//    服务端返回详细错误信息给客户端，默认为 true。
    private Boolean detailedErrorsToClient = true ;
    
//    只能用这些端口
//    private 
//    # Only allow frpc to bind ports you list. By default, there won't be any limit.
//    allowPorts = [
//    { start = 2000, end = 3000 },
//    { single = 3001 },
//    { single = 3003 },
//    { start = 4000, end = 50000 }
//    ]
    
//    限制单个客户端最大同时存在的代理数，默认无限制。
    private Integer maxPortsPerClient = 0 ;
    
//    # If subDomainHost is not empty, you can set subdomain when type is http or https in frpc's configure file
//    # When subdomain is test, the host used by routing is test.frps.com
//    二级域名后缀。
//    subDomainHost = "frps.com"
    
//    自定义 404 错误页面地址。
    private String custom404Page = "/path/to/404.html";
    
//    代理 UDP 服务时支持的最大包长度，默认为 1500，服务端和客户端的值需要一致。
    private Long udpPacketSize = 1500L;
    
//    打洞策略数据的保留时间，默认为 168 小时，即 7 天
    private Integer natholeAnalysisDataReserveHours = 168;
    
    /**
     * 建立连接的等待时间
     */
    private Integer userConnTimeout = 10;
    
    //ssh隧道网关
//    private SshTunnelGateway sshTunnelGateway ;
    
    public FrpConfigS(@NotNull String host, @NotNull Integer port) {
        super(host, port);
    }
}
