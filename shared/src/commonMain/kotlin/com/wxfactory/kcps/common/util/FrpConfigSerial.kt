package com.wxfactory.kcps.common.util

import cn.hutool.core.util.StrUtil
import cn.hutool.json.JSONObject
import com.wxfactory.kcps.frpfun.entity.FrpConfigC
import com.wxfactory.kcps.frpfun.entity.FrpcTypes
import com.wxfactory.kcps.frpfun.entity.auths.MethodType
import com.wxfactory.kcps.frpfun.entity.auths.TokenAuth
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder


object FrpConfigSerial : KSerializer<FrpConfigC> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("frpc", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: FrpConfigC) {
        var classStr : String = ""
        classStr+="type:${value.type},"
        classStr+= "id:${value.id},"
        
        if (StrUtil.isEmpty(value.name)){
            classStr+= "name:默认,"
        }else{
            classStr+= "name:${value.name},"
        }
        
        value.host?.let {
            classStr+= "host:${value.host},"
        }
        
        value.port?.let {
            classStr+= "port:${value.port},"
        }
        
        
        
        value.enabled?.let {
            classStr+= "enabled:${value.enabled},"
        }
        value.localIP?.let {
            classStr+="localIP:${value.localIP},"
        }
       
     
        value.localPort?.let {
            classStr+="localPort:${value.localPort},"
        }
        
        value.authentication?.let {
            when(it) {
                is TokenAuth -> {
                    classStr += "method:${it.method},"
                    classStr += "token:${it.token},"
                }
                else -> throw Exception("没有找到对应的认证类")
            }
        }
        
        if (value is TcpFcc){
            value.remotePort?.let {
                classStr+="remotePort:${value.remotePort},"
            }
          
        }
        if (value is UdpFcc){
            value.remotePort?.let {
                classStr+="remotePort:${value.remotePort},"
            }
        }
      
        if (value is StcpFcc){
            value.serverName?.let {
                classStr+="serverName:${value.serverName},"
            }
            value.secretKey?.let { 
                classStr+="secretKey:${value.secretKey},"
            }
            value.side?.let {
                classStr+="side:${value.side},"
            }
        }

        if (value is XtcpFcc){
            value.serverName?.let {
                classStr+="serverName:${value.serverName},"
            }
            value.secretKey?.let {
                classStr+="secretKey:${value.secretKey},"
            }
            value.side?.let {
                classStr+="side:${value.side},"
            }
        }
        classStr.removeSuffix(",")
        encoder.encodeString(classStr)

    }

    override fun deserialize(decoder: Decoder): FrpConfigC {
        val string = decoder.decodeString()
        var fcc: FrpConfigC? = null
        var type: String? = null
        var name: String? = null
        var id: String? = null
        var host: String? = null
        var port: Int? = null
        var enabled: Boolean? = null

        var localIP: String? = null
        var localPort: Int? = null
        var remotePort: Int? = null
        var side: String? = null
        var secretKey: String? = null
        var serverName: String? = null

        var method: String? = null
        var token: String? = null
        string?.let {
            val prs = it.split(",")
            for (pr in prs) {
                val thisOne = pr.split(":")
                if (thisOne.size == 2) {
                    when (thisOne[0]) {
                        "type" -> type = thisOne[1]
                        "name" -> name = thisOne[1]
                        "id" -> id = thisOne[1]
                        "host" -> host = thisOne[1]
                        "port" -> port = thisOne[1].toInt()
                        "enabled" -> enabled = thisOne[1].toBoolean()
                        "method" -> method = thisOne[1]
                        "localIP" -> localIP = thisOne[1] 
                        "localPort" -> localPort = thisOne[1].toInt()
                        "remotePort" -> remotePort = thisOne[1].toInt()
                        "side" -> side = thisOne[1]
                        "secretKey" -> secretKey = thisOne[1]
                        "serverName" -> serverName = thisOne[1]
                        "token" -> token = thisOne[1]
                    }
                }
            }
            when (type) {
                FrpcTypes.TCP.name -> {
                    fcc = TcpFcc(host, port!!)
                    (fcc as TcpFcc).remotePort = remotePort
                }

                FrpcTypes.UDP.name -> {
                    fcc = UdpFcc(host, port!!);
                    (fcc as UdpFcc).remotePort = remotePort
                }

                FrpcTypes.STCP.name -> {
                    fcc = StcpFcc(host, port!!);
                    (fcc as StcpFcc).side = side
                    (fcc as StcpFcc).secretKey = secretKey
                    (fcc as StcpFcc).serverName = serverName
                }

                FrpcTypes.XTCP.name -> {
                    fcc = XtcpFcc(host, port!!);
                    (fcc as XtcpFcc).side = side
                    (fcc as XtcpFcc).secretKey = secretKey
                    (fcc as XtcpFcc).serverName = serverName
                }

            }
            fcc?.name = name
            fcc?.id = id
            fcc?.host = host
            fcc?.port = port
            fcc?.enabled = enabled
            method?.let {
                when (method) {
                    MethodType.TOKEN.name -> {
                        fcc?.authentication = TokenAuth(MethodType.TOKEN, token)
                    }
                    MethodType.OIDC.name -> TODO("还没有设置这个认证")
                    else -> throw Exception("没有找到对应的认证类")
                }
            }
            fcc?.localIP = localIP
            fcc?.localPort = localPort
            
        }
        return fcc!!
    }
}