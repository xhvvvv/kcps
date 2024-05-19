package com.wxfactory.kcps.common.tabs

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.wxfactory.kcps.common.core.entity.FrpConfigCCompose
import com.wxfactory.kcps.common.public.*
import com.wxfactory.kcps.common.public.validate.MaxLValidator
import com.wxfactory.kcps.common.public.validate.intValidator
import com.wxfactory.kcps.common.public.validate.nnullValidator
import com.wxfactory.kcps.frpfun.entity.FrpConfig
import com.wxfactory.kcps.frpfun.entity.FrpConfigC
import com.wxfactory.kcps.frpfun.entity.FrpcTypes
import com.wxfactory.kcps.frpfun.entity.auths.MethodType
import com.wxfactory.kcps.frpfun.entity.auths.TokenAuth
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.StcpFcc
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.TcpFcc
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.XtcpFcc

/**kind 2自定义的*/
private data class Address(val ip: String, val port: Int?, val kind: Int, val fc: FrpConfig?) {
    override fun toString(): String {
        if (port == null) {
            return "$ip"
        } else {
            return "$ip:$port"
        }
    }
}

class AddPanel(
    val fcs: MutableList<FrpConfigCCompose<FrpConfigC>>,
    val doneCallBack: (tfc: FrpConfigC) -> Unit
) : Screen {
    @Composable
    override fun Content() {
        Form {
            val formState = LocalFormState.current
            LaunchedEffect(Unit){
                //注册校验器
                formState{
                    "configName" useValidators listOf(
                        nnullValidator,
                        MaxLValidator(10)
                    )
                    "port" useValidators listOf(
                        intValidator
                    )
                }
            }
            var chosed by remember { mutableStateOf(FrpcTypes.TCP ) }
            var name by remember { mutableStateOf("默认") }
            var ip by remember { mutableStateOf("localhost") }
            var port by remember { mutableStateOf("8080") }
            var cs by remember { mutableStateOf(false) }
            val allServer: List<Address> by remember(fcs.size) {
                derivedStateOf {
                    val thisList: MutableList<Address> = mutableListOf()
                    fcs.mapTo(thisList) { x ->
                        Address(x.fc.host, x.fc.port, 1, x.fc)
                    }
                    thisList.add(Address(ip = "自定义", port = null, kind = 2, null))
                    thisList.distinctBy { x -> "${x.ip}:${x.port}" }
                }
            }
            var chosedAddress: Address by remember { mutableStateOf(allServer.first()) }

            val authType by remember {
                derivedStateOf {
                    MethodType.values().toList()
                }
            }
            var chosedAuthType by remember { mutableStateOf(authType[0]) }
            var token by remember { mutableStateOf("") }
            Scaffold(
                modifier = Modifier.fillMaxHeight(0.6f),
                bottomBar = {
                    Card {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(5.dp),
                            horizontalArrangement = Arrangement.End, // 从末尾开始排列
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            FilledTonalButton(
                                onClick = {
                                    if(formState.virfyAll()){
                                        val tfc: FrpConfigC =  
                                            FrpConfigC.mapperClass.get(chosed)!!
                                                .getDeclaredConstructor(
                                                    String::class.java,
                                                    Int::class.javaPrimitiveType
                                                ).newInstance(
                                                    ip,port.toInt()
                                                )
                                        
                                        tfc.name = name
                                        tfc.authentication = TokenAuth(chosedAuthType, token)
                                        if( tfc is XtcpFcc  ){
                                            tfc.side = if(cs) FrpConfigC.W_S_S else FrpConfigC.W_S_C
                                        }
                                        
                                        if( tfc is StcpFcc ){
                                            tfc.side = if(cs) FrpConfigC.W_S_S else FrpConfigC.W_S_C
                                        }
                                        
                                        doneCallBack(tfc)
                                    }else{
                                         
                                    }
                                }
                            ) {
                                Text("添加")
                            }
                        }
                    }
                }
            ) {
                Card(
                    modifier = Modifier.fillMaxSize()
                ) {
                    addRow {
                        Select<FrpcTypes>(
                            modifier = Modifier.fillMaxWidth(0.3f),
                            options = FrpcTypes.values().toList(),
                            onOptionSelected = { ii -> chosed = ii  }
                        ) {
                            Text(
                                style = MaterialTheme.typography.labelLarge.copy(
                                    fontWeight = FontWeight.SemiBold,
                                ), text = "选择连接类型"
                            )
                        }
                        InputNo1(
                            id = "configName",
                            modifier = Modifier.fillMaxWidth(0.3f),
                            title = "配置名称",
                            type = KeyboardType.Text,
                            currentValue = name,
                            onValueChange = {
                                name = it?:""
                            },
                        )

                        if ( chosed == FrpcTypes.XTCP || chosed == FrpcTypes.STCP  ){
                            CheckBox(
                                currentValue = cs,
                                onValueChange = {
                                    cs = it
                                }
                            ){
                                Text(
                                    style = MaterialTheme.typography.labelLarge.copy(
                                        fontWeight = FontWeight.SemiBold,
                                    ), text = "服务端"
                                )
                            }
                        }else{
                            cs = false
                        }
                    }

                    addRow {
                        Select<Address>(
                            modifier = Modifier.fillMaxWidth(0.3f),
                            options = allServer,
                            selectedOptionShow = { "${it.ip}:${it.port ?: ""}" },
                            optionSelectedShow = { "${it.ip}:${it.port ?: ""}" },
                            onOptionSelected = { address -> chosedAddress = address }
                        ) {
                            Text(
                                style = MaterialTheme.typography.labelLarge.copy(
                                    fontWeight = FontWeight.SemiBold,
                                ), text = "选择公网host"
                            )
                        }

                        if (chosedAddress.kind == 2) {
                            InputNo1(
                                modifier = Modifier.fillMaxWidth(0.3f),
                                title = "公网host",
                                type = KeyboardType.Text,
                                currentValue = ip ,
                                onValueChange = {
                                    ip = it?:""
                                },
                            )
                            InputNo1(
                                id="port",
                                modifier = Modifier.fillMaxWidth(0.3f),
                                title = "公网port",
                                type = KeyboardType.Number,
                                currentValue = port,
                                onValueChange = {
                                    port = it?:""
                                },
                            )
                        }
                    }
                    addRow {
                        Select<MethodType>(
                            modifier = Modifier.fillMaxWidth(0.3f),
                            options = authType,
                            selectedOptionShow = { it.name },
                            optionSelectedShow = { it.name },
                            onOptionSelected = { type -> chosedAuthType = type }
                        ) {
                            Text(
                                style = MaterialTheme.typography.labelLarge.copy(
                                    fontWeight = FontWeight.SemiBold,
                                ), text = "认证方式"
                            )
                        }

                        when (chosedAuthType) {
                            MethodType.TOKEN -> {
                                InputNo1(
                                    modifier = Modifier.fillMaxWidth(0.7f),
                                    title = "密匙",
                                    type = KeyboardType.Password,
                                    currentValue = token,
                                    onValueChange = {
                                        token = it?:""
                                    },
                                )
                            }

                            MethodType.OIDC -> TODO()
                        }
                    }
                }

            }
        }
    }
}

@Composable
private fun addRow(
    content: @Composable () -> Unit
) {
    Row(
        modifier = Modifier.padding(6.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        content()
    }
} 