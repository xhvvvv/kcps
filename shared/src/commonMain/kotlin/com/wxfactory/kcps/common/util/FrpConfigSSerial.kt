package com.wxfactory.kcps.common.util

import cn.hutool.core.util.StrUtil
import com.wxfactory.kcps.frpfun.entity.FrpConfigC
import com.wxfactory.kcps.frpfun.entity.FrpConfigS
import com.wxfactory.kcps.frpfun.entity.FrpcTypes
import com.wxfactory.kcps.frpfun.entity.auths.MethodType
import com.wxfactory.kcps.frpfun.entity.auths.TokenAuth
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.StcpFcc
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.TcpFcc
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.UdpFcc
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.XtcpFcc
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder


object FrpConfigSSerial : KSerializer<FrpConfigS> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("frpc", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: FrpConfigS) {
        var classStr : String = ""
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
        
        value.authentication?.let {
            when(it) {
                is TokenAuth -> {
                    classStr += "method:${it.method},"
                    classStr += "token:${it.token},"
                }
                else -> throw Exception("没有找到对应的认证类")
            }
        }

        value.kcpBindPort?.let {
            classStr+= "kcpBindPort:${value.kcpBindPort},"
        }
        
        value.quicBindPort?.let {
            classStr+= "quicBindPort:${value.quicBindPort},"
        }
        
        value.tcpmuxHTTPConnectPort?.let {
            classStr+= "tcpmuxHTTPConnectPort:${value.tcpmuxHTTPConnectPort},"
        }
        
        classStr.removeSuffix(",")
        encoder.encodeString(classStr)

    }

    override fun deserialize(decoder: Decoder): FrpConfigS {
        val string = decoder.decodeString()
        var name: String? = null
        var id: String? = null
        var host: String? = null
        var port: Int? = null
        var enabled: Boolean? = null

        var method: String? = null
        var token: String? = null
        var kcpBindPort: Int? = null
        var quicBindPort: Int? = null
        var tcpmuxHTTPConnectPort: Int? = null
        var fcc: FrpConfigS? = null 
        string?.let {
            val prs = it.split(",")
            for (pr in prs) {
                val thisOne = pr.split(":")
                if (thisOne.size == 2) {
                    when (thisOne[0]) {
                        "name" -> name = thisOne[1]
                        "id" -> id = thisOne[1]
                        "host" -> host = thisOne[1]
                        "port" -> port = thisOne[1].toInt()
                        "enabled" -> enabled = thisOne[1].toBoolean()
                        "method" -> method = thisOne[1]
                        "token" -> token = thisOne[1]
                        "kcpBindPort" -> kcpBindPort = thisOne[1].toInt()
                        "quicBindPort" -> quicBindPort = thisOne[1].toInt()
                        "tcpmuxHTTPConnectPort" -> tcpmuxHTTPConnectPort = thisOne[1].toInt()
                    }
                }
            }
            fcc = FrpConfigS(host!!, port!!)
            fcc?.apply {
                this.name = name
                this.id = id
                this.enabled = enabled
                method?.let {
                    when (method) {
                        MethodType.TOKEN.name -> {
                            this.authentication = TokenAuth(MethodType.TOKEN, token)
                        }
                        MethodType.OIDC.name -> TODO("还没有设置这个认证")
                        else -> throw Exception("没有找到对应的认证类")
                    }
                }

                this.kcpBindPort = kcpBindPort
                this.quicBindPort = quicBindPort
                this.tcpmuxHTTPConnectPort = tcpmuxHTTPConnectPort
            }
           
            
        }
        return fcc!!
    }
}