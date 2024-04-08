package com.wxfactory.kcps.common.tabs

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.wxfactory.kcps.common.public.InputNo1
import com.wxfactory.kcps.common.public.Select
import com.wxfactory.kcps.common.public.TextFieldState
import com.wxfactory.kcps.frpfun.entity.FrpConfig
import com.wxfactory.kcps.frpfun.entity.FrpcTypes
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.TcpFcc


class AddPanel(
    val fcs:MutableList<FrpConfig>,
    val doneCallBack : () -> Unit
) : Screen {
    @Composable
    override fun Content() {
        var chosed by remember { mutableStateOf(FrpcTypes.TCP.name) }
        var name by remember { mutableStateOf("默认") }
        val allServer:List<String> by remember(fcs.size) {
            derivedStateOf {
                fcs.map { x -> "${x.host}:${x.port}" }
            }
        }
        
        
        Scaffold(
            modifier = Modifier.fillMaxHeight(0.5f) ,
            bottomBar = {
                Card {
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End, // 从末尾开始排列
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        FilledTonalButton(
                            onClick = {
                                FrpcTypes.valueOf(chosed)
                                val tfc: TcpFcc = TcpFcc("lkasdjfl",21)
                                tfc.name = "行增"
                                doneCallBack()
                                fcs.add(tfc)
                            }
                        ) {
                            Text("添加")
                        }
                    }
                }
            }
        ){
                Card( 
                    modifier = Modifier.fillMaxSize()
                ){
                    Row  {
                        Select<FrpcTypes>(
                            modifier = Modifier.fillMaxWidth(0.3f),
                            options = FrpcTypes.values().toList(),
                            selectedOption = TextFieldState(chosed,null),
                            onOptionSelected = { ii -> chosed =ii.name }
                        ){
                            Text(  
                                style = MaterialTheme.typography.labelLarge.copy(
                                            fontWeight = FontWeight.SemiBold,
                                        )
                                ,text = "选择连接类型"
                            )
                        }

                        Select<String>(
                            modifier = Modifier.fillMaxWidth(0.3f),
                            options = allServer,
                            selectedOption = TextFieldState(chosed,null),
                            onOptionSelected = { }
                        ){
                            Text(
                                style = MaterialTheme.typography.labelLarge.copy(
                                    fontWeight = FontWeight.SemiBold,
                                )
                                ,text = "选择公网host"
                            )
                        }
                    }

                    Row {
                        InputNo1(
                            modifier = Modifier.fillMaxWidth(0.3f),
                            title = "配置名称",
                            currentValue = name,
                            onValueChange = {
                                name = it
                            },
                        )
                    }
                }
                 
            }
        }
}
