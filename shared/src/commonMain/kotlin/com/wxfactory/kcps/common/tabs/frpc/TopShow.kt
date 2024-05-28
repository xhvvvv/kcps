package com.wxfactory.kcps.common.tabs.frpc

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.wxfactory.kcps.common.core.entity.FrpConfigCCompose
import com.wxfactory.kcps.common.public.CheckBox
import com.wxfactory.kcps.common.public.InputNo1
import com.wxfactory.kcps.common.util.i18N
import com.wxfactory.kcps.frpfun.entity.FrpConfigC
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.*


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun topShow(
    modifier: Modifier = Modifier,
    fc: FrpConfigCCompose<FrpConfigC>,
) {
    FlowRow(
        modifier = modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        maxItemsInEachRow = 5
    ) {
        InputNo1(
            id = "name",
            modifier = Modifier.width(200.dp),
            title = i18N.getProperty("cg-name"),
            labelTextStyle = MaterialTheme.typography.labelSmall,
            type = KeyboardType.Text,
            currentValue = fc.fc.name?:"",
            onValueChange = {
                fc.fc.name = it?: ""
            },
            editable = fc.getIfBusy().not()
        )

        InputNo1(
            id = "host",
            modifier = Modifier.width(200.dp),
            title = i18N.getProperty("cg-host"),
            labelTextStyle = MaterialTheme.typography.labelSmall,
            type = KeyboardType.Text,
            currentValue = fc.fc.host?:"",
            onValueChange = {
                fc.fc.host = it?: ""
            },
            editable = fc.getIfBusy().not()
        )

        InputNo1(
            id = "port",
            modifier = Modifier.width(200.dp),
            title = i18N.getProperty("cg-port"),
            labelTextStyle = MaterialTheme.typography.labelSmall,
            type = KeyboardType.Number,
            currentValue = fc.fc.port?.toString()?:"",
            onValueChange = {
                fc.fc.port = it?.toInt()
            },
            editable = fc.getIfBusy().not()
        )


        when (fc.fc) {
            is XtcpFcc -> {
                if (FrpConfigC.W_S_C.equals(fc.fc.side)){
                    InputNo1(
                        id = "serverName",
                        modifier = Modifier.width(200.dp),
                        title = i18N.getProperty("cg-serverName"),
                        labelTextStyle = MaterialTheme.typography.labelSmall,
                        currentValue = fc.fc.serverName?.toString() ?: "",
                        onValueChange = {
                            fc.fc.serverName = it?: ""
                        },
                        editable = fc.getIfBusy().not()
                    )
                }
                InputNo1(
                    id = "secretKey",
                    modifier = Modifier.width(200.dp),
                    title = i18N.getProperty("cg-secretKey"),
                    labelTextStyle = MaterialTheme.typography.labelSmall,
                    type = KeyboardType.Number,
                    currentValue =  fc.fc.secretKey?.toString() ?: "",
                    onValueChange = {
                        fc.fc.secretKey = it?: ""
                    },
                    editable = fc.getIfBusy().not()
                )
                InputNo1(
                    id = "localIP",
                    modifier = Modifier.width(200.dp),
                    title = i18N.getProperty("cg-localIP"),
                    labelTextStyle = MaterialTheme.typography.labelSmall,
                    currentValue = fc.fc.localIP?.toString() ?: "",
                    onValueChange = {
                        fc.fc.localIP = it?: ""
                    },
                    editable = fc.getIfBusy().not()
                )
                InputNo1(
                    id = "localPort",
                    modifier = Modifier.width(200.dp),
                    title = i18N.getProperty("cg-localPort"),
                    labelTextStyle = MaterialTheme.typography.labelSmall,
                    type = KeyboardType.Number,
                    currentValue = fc.fc.localPort?.toString() ?: "",
                    onValueChange = {
                        fc.fc.localPort = it?.toInt()
                    },
                    editable = fc.getIfBusy().not()
                )
                CheckBox(
                    modifier = Modifier.width(200.dp),
                    currentValue = fc.fc.keepTunnelOpen ?: false,
                    onValueChange = {
                        fc.fc.keepTunnelOpen = it
                    },
                    editable = fc.getIfBusy().not()
                ) {
                    Text(
                        "保持p2p心跳",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.SemiBold,
                        )
                    )
                }
            }

            is StcpFcc -> {

                if (FrpConfigC.W_S_C.equals(fc.fc.side)){
                    InputNo1(
                        id = "serverName",
                        modifier = Modifier.width(200.dp),
                        title = i18N.getProperty("cg-serverName"),
                        labelTextStyle = MaterialTheme.typography.labelSmall,
                        currentValue = fc.fc.serverName?.toString() ?: "",
                        onValueChange = {
                            fc.fc.serverName = it?: ""
                        },
                        editable = fc.getIfBusy().not()
                    )
                }
                InputNo1(
                    id = "localIP",
                    modifier = Modifier.width(200.dp),
                    title = i18N.getProperty("cg-localIP"),
                    labelTextStyle = MaterialTheme.typography.labelSmall,
                    currentValue = fc.fc.localIP ?: "",
                    onValueChange = {
                        fc.fc.localIP = it?: ""
                    },
                    editable = fc.getIfBusy().not()
                )
                InputNo1(
                    id = "localPort",
                    modifier = Modifier.width(200.dp),
                    title = i18N.getProperty("cg-localPort"),
                    labelTextStyle = MaterialTheme.typography.labelSmall,
                    type = KeyboardType.Number,
                    currentValue = fc.fc.localPort?.toString() ?: "",
                    onValueChange = {
                        fc.fc.localPort = it?.toInt()
                    },
                    editable = fc.getIfBusy().not()
                )
                InputNo1(
                    id = "secretKey",
                    modifier = Modifier.width(200.dp),
                    title = i18N.getProperty("cg-secretKey"),
                    labelTextStyle = MaterialTheme.typography.labelSmall,
                    type = KeyboardType.Password,
                    currentValue =  fc.fc.secretKey?.toString() ?: "",
                    onValueChange = {
                        fc.fc.secretKey = it?: ""
                    },
                    editable = fc.getIfBusy().not()
                )
            }

            is TcpmuxFcc -> {
                InputNo1(
                    modifier = Modifier.width(200.dp),
                    title = i18N.getProperty("cg-multiplexer"),
                    labelTextStyle = MaterialTheme.typography.labelSmall,
                    currentValue = fc.fc.multiplexer ?: "",
                    onValueChange = {
                        fc.fc.multiplexer = it
                    },
                    editable = fc.getIfBusy().not()
                )
                InputNo1(
                    modifier = Modifier.width(200.dp),
                    title = i18N.getProperty("cg-customDomains"),
                    labelTextStyle = MaterialTheme.typography.labelSmall,
                    currentValue = fc.fc.customDomains ?: "",
                    onValueChange = {
                        fc.fc.customDomains = it?: ""
                    },
                    editable = fc.getIfBusy().not()
                )
                InputNo1(
                    modifier = Modifier.width(200.dp),
                    title = i18N.getProperty("cg-localIP"),
                    labelTextStyle = MaterialTheme.typography.labelSmall,
                    currentValue = fc.fc.localIP ?: "",
                    onValueChange = {
                        fc.fc.localIP = it?: ""
                    },
                    editable = fc.getIfBusy().not()
                )
                InputNo1(
                    modifier = Modifier.width(200.dp),
                    title = i18N.getProperty("cg-localPort"),
                    labelTextStyle = MaterialTheme.typography.labelSmall,
                    type = KeyboardType.Number,
                    currentValue = fc.fc.localPort?.toString() ?: "",
                    onValueChange = {
                        fc.fc.localPort = it?.toInt()
                    },
                    editable = fc.getIfBusy().not()
                )
            }

            is TcpFcc -> {
                InputNo1(
                    id = "localIP",
                    modifier = Modifier.width(200.dp),
                    title = i18N.getProperty("cg-localIP"),
                    labelTextStyle = MaterialTheme.typography.labelSmall,
                    currentValue = fc.fc.localIP ?: "",
                    onValueChange = {
                        fc.fc.localIP = it?: ""
                    },
                    editable = fc.getIfBusy().not()
                )
                InputNo1(
                    id = "localPort",
                    modifier = Modifier.width(200.dp),
                    title = i18N.getProperty("cg-localPort"),
                    labelTextStyle = MaterialTheme.typography.labelSmall,
                    type = KeyboardType.Number,
                    currentValue = fc.fc.localPort?.toString() ?: "",
                    onValueChange = {
                        fc.fc.localPort = it?.toInt()
                    },
                    editable = fc.getIfBusy().not()
                )
                InputNo1(
                    id = "remotePort",
                    modifier = Modifier.width(200.dp),
                    title = i18N.getProperty("cg-remotePort"),
                    labelTextStyle = MaterialTheme.typography.labelSmall,
                    type = KeyboardType.Number,
                    currentValue = fc.fc.remotePort?.toString() ?: "",
                    onValueChange = {
                        fc.fc.remotePort = it?.toInt()
                    },
                    editable = fc.getIfBusy().not()
                )
            }

            is UdpFcc -> {
                InputNo1(
                    id = "localIP",
                    modifier = Modifier.width(200.dp),
                    title = i18N.getProperty("cg-localIP"),
                    labelTextStyle = MaterialTheme.typography.labelSmall,
                    currentValue = fc.fc.localIP.toString()?:"",
                    onValueChange = {
                        fc.fc.localIP = it?: ""
                    },
                    editable = fc.getIfBusy().not()
                )
                InputNo1(
                    id = "localPort",
                    modifier = Modifier.width(200.dp),
                    title = i18N.getProperty("cg-localPort"),
                    labelTextStyle = MaterialTheme.typography.labelSmall,
                    type = KeyboardType.Number,
                    currentValue = fc.fc.localPort?.toString() ?: "",
                    onValueChange = {
                        fc.fc.localPort = it?.toInt()
                    },
                    editable = fc.getIfBusy().not()
                )
                InputNo1(
                    id = "remotePort",
                    modifier = Modifier.width(200.dp),
                    title = i18N.getProperty("cg-remotePort"),
                    labelTextStyle = MaterialTheme.typography.labelSmall,
                    type = KeyboardType.Number,
                    currentValue = fc.fc.remotePort?.toString() ?: "",
                    onValueChange = {
                        fc.fc.remotePort = it?.toInt()
                    },
                    editable = fc.getIfBusy().not()
                )
            }

            else -> { // 注意这个块
                println("x 不是 1 ，也不是 2")
            }
        }
    }
}