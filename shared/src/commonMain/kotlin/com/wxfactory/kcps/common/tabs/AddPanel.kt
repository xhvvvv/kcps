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
import com.wxfactory.kcps.frpfun.entity.FrpConfigC
import com.wxfactory.kcps.frpfun.entity.FrpcTypes
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.TcpFcc

/**kind 2自定义的*/
private data class Address(val ip:String,val port:Int? ,val kind:Int,val fc:FrpConfig?) {
    
    override fun toString(): String {
        if (port==null){
            return "$ip"
        }else{
            return "$ip:$port"
        }
    }

 
}
class AddPanel(
    val fcs:MutableList<FrpConfig>,
    val doneCallBack : (tfc: FrpConfigC) -> Unit
) : Screen {
    @Composable
    override fun Content() {
        var chosed by remember { mutableStateOf(FrpcTypes.TCP.name) }
        var name   by remember { mutableStateOf("默认") }
        var ip   by remember { mutableStateOf("localhost") }
        var port   by remember { mutableStateOf("8080") }
        val allServer:List<Address> by remember(fcs.size) {
            derivedStateOf {
                val thisList: MutableList<Address> = mutableListOf()
                fcs.mapTo(thisList){ 
                    x-> Address(x.host,x.port,1,x)
                }
                thisList.add(Address(ip="自定义",port = null, kind = 2 , null))
                thisList.distinctBy { x-> "${x.ip}:${x.port}"}
            }
        }
        var chosedAddress:Address  by remember { mutableStateOf(allServer.first()) }
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
                                
                                val tfc: FrpConfigC  
                                if(chosedAddress.kind == 2 ){
                                    tfc = TcpFcc(ip,port.toInt())
                                }else{
                                    tfc = (chosedAddress.fc as FrpConfigC).newMyFccWithType(FrpcTypes.valueOf(chosed))
                                }
                                tfc.name = name
                                doneCallBack(tfc)
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
                        InputNo1(
                            modifier = Modifier.fillMaxWidth(0.3f),
                            title = "配置名称",
                            currentValue = name,
                            onValueChange = {
                                name = it
                            },
                        )
                       
                    }

                    Row {
                        Select<Address>(
                            modifier = Modifier.fillMaxWidth(0.3f),
                            options = allServer,
                            selectedOption = TextFieldState("${chosedAddress.ip}:${chosedAddress.port?:""}",null),
                            onOptionSelected = { address -> chosedAddress = address }
                        ){
                            Text(
                                style = MaterialTheme.typography.labelLarge.copy(
                                    fontWeight = FontWeight.SemiBold,
                                )
                                ,text = "选择公网host"
                            )
                        }
                        
                        if(chosedAddress .kind ==2){
                            InputNo1(
                                modifier = Modifier.fillMaxWidth(0.3f),
                                title = "公网ip",
                                currentValue = ip,
                                onValueChange = {
                                    ip = it
                                },
                            )
                            InputNo1(
                                modifier = Modifier.fillMaxWidth(0.3f),
                                title = "公网port",
                                currentValue = port,
                                onValueChange = {
                                    port = it
                                },
                            )
                        }
                    }
                }
                 
            }
        }
}
