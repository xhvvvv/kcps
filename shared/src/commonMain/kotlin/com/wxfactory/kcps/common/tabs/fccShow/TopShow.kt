package com.wxfactory.kcps.common.tabs.fccShow

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.wxfactory.kcps.common.public.CheckBox
import com.wxfactory.kcps.common.public.InputNo1
import com.wxfactory.kcps.common.util.i18N
import com.wxfactory.kcps.frpfun.entity.FrpConfigC
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.*


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun topShow(
    modifier: Modifier = Modifier,
    fc: FrpConfigC,
){
    FlowRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        maxItemsInEachRow = 5
    ){
        InputNo1(
            modifier = Modifier.width(200.dp),
            title = i18N.getProperty("cg-name"),
            currentValue = fc.name,
            onValueChange = {
                fc.name = it
            },
        )

        InputNo1(
            modifier = Modifier.width(200.dp),
            title = i18N.getProperty("cg-host"),
            currentValue = fc.host,
            onValueChange = {
                fc.host = it
            },
        )

        InputNo1(
            modifier = Modifier.width(200.dp),
            title = i18N.getProperty("cg-port"),
            currentValue = fc.port.toString(),
            onValueChange = {
                fc.port = it.toInt()
            },
        )


        when (fc) {
            is XtcpFcc -> {
                InputNo1(
                    modifier = Modifier.width(200.dp),
                    title = i18N.getProperty("cg-serverName"),
                    currentValue = fc.localIP?:"",
                    onValueChange = {
                        fc.localIP = it
                    },
                )
                InputNo1(
                    modifier = Modifier.width(200.dp),
                    title = i18N.getProperty("cg-secretKey"),
                    currentValue = fc.localPort?.toString()?:"" ,
                    onValueChange = {
                        fc.localPort = it.toInt()
                    },
                )
                InputNo1(
                    modifier = Modifier.width(200.dp),
                    title = i18N.getProperty("cg-bindAddr"),
                    currentValue = fc.remotePort?.toString()?:"",
                    onValueChange = {
                        fc.remotePort = it.toInt()
                    },
                )
                InputNo1(
                    modifier = Modifier.width(200.dp),
                    title = i18N.getProperty("cg-bindPort"),
                    currentValue = fc.bindPort?.toString()?:"",
                    onValueChange = {
                        fc.bindPort = it.toInt()
                    },
                )
                CheckBox(
                    modifier = Modifier.width(200.dp),
                ){
                    Text("保持p2p心跳",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.SemiBold,
                        )
                    )
                }
            }
            is StcpFcc -> {
                InputNo1(
                    modifier = Modifier.width(200.dp),
                    title = i18N.getProperty("cg-localIP"),
                    currentValue = fc.localIP.toString(),
                    onValueChange = {
                        fc.localIP = it
                    },
                )
                InputNo1(
                    modifier = Modifier.width(200.dp),
                    title = i18N.getProperty("cg-localPort"),
                    currentValue = fc.localPort.toString(),
                    onValueChange = {
                        fc.localPort = it.toInt()
                    },
                )
                InputNo1(
                    modifier = Modifier.width(200.dp),
                    title = i18N.getProperty("cg-secretKey"),
                    currentValue = fc.secretKey?:"",
                    onValueChange = {
                        fc.secretKey = it
                    },
                )
            }
            is TcpmuxFcc -> {
                InputNo1(
                    modifier = Modifier.width(200.dp),
                    title = i18N.getProperty("cg-multiplexer"),
                    currentValue = fc.multiplexer?:"",
                    onValueChange = {
                        fc.multiplexer = it
                    },
                )
                InputNo1(
                    modifier = Modifier.width(200.dp),
                    title = i18N.getProperty("cg-customDomains"),
                    currentValue = fc.customDomains?:"",
                    onValueChange = {
                        fc.customDomains = it 
                    },
                )
                InputNo1(
                    modifier = Modifier.width(200.dp),
                    title = i18N.getProperty("cg-localIP"),
                    currentValue = fc.localIP?:"",
                    onValueChange = {
                        fc.localIP = it
                    },
                )
                InputNo1(
                    modifier = Modifier.width(200.dp),
                    title = i18N.getProperty("cg-localPort"),
                    currentValue = fc.localPort?.toString()?:"" ,
                    onValueChange = {
                        fc.localPort = it.toInt()
                    },
                )
            }
            is TcpFcc -> {
                InputNo1(
                    modifier = Modifier.width(200.dp),
                    title = i18N.getProperty("cg-localIP"),
                    currentValue = fc.localIP?:"",
                    onValueChange = {
                        fc.localIP = it
                    },
                )
                InputNo1(
                    modifier = Modifier.width(200.dp),
                    title = i18N.getProperty("cg-localPort"),
                    currentValue = fc.localPort?.toString()?:"" ,
                    onValueChange = {
                        fc.localPort = it.toInt()
                    },
                )
                InputNo1(
                    modifier = Modifier.width(200.dp),
                    title = i18N.getProperty("cg-remotePort"),
                    currentValue = fc.remotePort?.toString()?:"" ,
                    onValueChange = {
                        fc.remotePort = it.toInt()
                    },
                )
            }
            is UdpFcc -> {
                InputNo1(
                    modifier = Modifier.width(200.dp),
                    title = i18N.getProperty("cg-localIP"),
                    currentValue = fc.localIP.toString(),
                    onValueChange = {
                        fc.localIP = it
                    },
                )
                InputNo1(
                    modifier = Modifier.width(200.dp),
                    title = i18N.getProperty("cg-localPort"),
                    currentValue = fc.localPort?.toString()?:"" ,
                    onValueChange = {
                        fc.localPort = it.toInt()
                    },
                )
                InputNo1(
                    modifier = Modifier.width(200.dp),
                    title = i18N.getProperty("cg-remotePort"),
                    currentValue = fc.remotePort?.toString()?:"" ,
                    onValueChange = {
                        fc.remotePort = it.toInt()
                    },
                )
            }
            
            else -> { // 注意这个块
                println("x 不是 1 ，也不是 2")
            }
        }

        
    }
}