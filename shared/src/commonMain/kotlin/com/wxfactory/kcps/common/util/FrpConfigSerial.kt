package com.wxfactory.kcps.common.util

import com.alibaba.fastjson2.JSONB
import com.alibaba.fastjson2.JSONObject
import com.wxfactory.kcps.frpfun.entity.FrpConfig
import com.wxfactory.kcps.frpfun.entity.FrpConfigC
import com.wxfactory.kcps.frpfun.entity.FrpcTypes
import com.wxfactory.kcps.frpfun.entity.auths.MethodType
import com.wxfactory.kcps.frpfun.entity.auths.TokenAuth
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.HttpFcc
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.StcpFcc
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.TcpFcc
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.TcpmuxFcc
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.UdpFcc
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.XtcpFcc
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialKind
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder


object FrpConfigSerial : KSerializer<FrpConfigC> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("frpc", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: FrpConfigC) {
        encoder.encodeString(JSONObject.toJSONString(value))
    }

    override fun deserialize(decoder: Decoder): FrpConfigC {
        val string = decoder.decodeString()
        val obj:JSONObject = JSONObject.parseObject(string)
        val fc : FrpConfigC 
        when(obj.getString("type")){
            FrpcTypes.TCP.name -> fc = JSONObject.parseObject(string,TcpFcc::class.java)
            FrpcTypes.HTTP.name -> fc =  JSONObject.parseObject(string,HttpFcc::class.java)
            FrpcTypes.UDP.name -> fc =  JSONObject.parseObject(string,UdpFcc::class.java)
            FrpcTypes.XTCP.name -> fc =  JSONObject.parseObject(string,XtcpFcc::class.java)
            FrpcTypes.STCP.name -> fc =  JSONObject.parseObject(string,StcpFcc::class.java)
            FrpcTypes.TCPMUX.name -> fc =  JSONObject.parseObject(string,TcpmuxFcc::class.java)
            else -> throw Exception("没有找到对应的配置类")
        }

        val  auth = obj.getJSONObject("authentication");
        when(auth.getString("method")){
            MethodType.TOKEN.name -> fc.authentication =  JSONObject.parseObject(auth.toJSONString(),TokenAuth::class.java)
            MethodType.OIDC.name -> TODO("还没有设置这个认证")
            else -> throw Exception("没有找到对应的认证类")
        }
        return fc
    }
}