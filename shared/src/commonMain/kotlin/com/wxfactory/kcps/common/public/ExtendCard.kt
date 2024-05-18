package com.wxfactory.kcps.common.public

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.wxfactory.kcps.common.core.entity.FrpConfigCCompose
import com.wxfactory.kcps.common.platform.frpfun.startFrpC
import com.wxfactory.kcps.common.platform.frpfun.stopFrpC
import com.wxfactory.kcps.frpfun.entity.FrpConfigC
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.StcpFcc
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.TcpFcc
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.UdpFcc
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.XtcpFcc
import com.wxfactory.kcps.frpfun.frpBash.entity.ExcuteCon
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun fccExtendCard(
    fc: FrpConfigCCompose<FrpConfigC>,
    modifier: Modifier = Modifier,
    removeFc : (FrpConfigCCompose<FrpConfigC>) ->Unit,
    onExpand: () -> Unit, //切换展开时的调用
    alert : (String) -> Unit,
    content: @Composable ( fc: FrpConfigCCompose<FrpConfigC> ) -> Unit,
) {
    val run = rememberCoroutineScope()
    var ifRunOnly : Boolean by mutableStateOf(fc.ifrunOnly ) 
    var loadding : Boolean by remember { mutableStateOf(false )}
    var enabled : Boolean by remember { mutableStateOf(fc.fc.enabled )}
    val formState = LocalFormState.current
    LaunchedEffect(Unit){
        //注册回调
        fc.exeStartCallBack = {
            fc.ifrunnig.value = true
        }
        fc.exeCallBack = {
            fc.ifrunnig.value = false
        }
    }
    
    Card(
        modifier = modifier,
        onClick = {
            onExpand()
            fc.expend.value = fc.expend.value.not()
        },
    ) {
        Column(
            modifier = modifier.padding(16.dp),
        ) {
            // 展示信息
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    //前
                    Column(modifier = Modifier.weight(0.1f)) {
                        Row {
                            Icon(
                                imageVector = Icons.Outlined.HourglassEmpty,
                                contentDescription = "配置名称",
                            )
                            Text(
                                text = fc.fc.name?:"",
                                style = MaterialTheme.typography.titleSmall,
                            )
                        }
                    }
                    //中
                    Column(modifier = Modifier.weight(0.5f)) {
                        Row(
//                            modifier = Modifier.background(Color.Gray),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Card(
                                modifier=Modifier.fillMaxWidth(0.2f).padding(start = 10.dp,end = 10.dp)
                            ) {
                                Icon(
                                    modifier = Modifier.align(Alignment.CenterHorizontally),
                                    imageVector = Icons.Outlined.Category,
                                    contentDescription = "协议类型",
                                )
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = fc.fc.type,
                                    style = MaterialTheme.typography.titleSmall,
                                    textAlign = TextAlign.Center
                                )
                            }
                            Divider(modifier= Modifier.width(5.dp).heightIn(min = 40.dp))
                            Card(
                                modifier=Modifier.fillMaxWidth(0.4f).padding(start = 10.dp,end = 10.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Column(
                                        modifier = Modifier.fillMaxHeight().fillMaxWidth(0.3f),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Icon(
                                            imageVector = Icons.Outlined.Circle,
                                            contentDescription = "占用端口",
                                        )
                                        Text(
                                            modifier = Modifier.fillMaxWidth(),
                                            text = fc.fc.localPort?.toString() ?: "",
                                            style = MaterialTheme.typography.titleSmall,
                                            textAlign = TextAlign.Center
                                        )
                                    }

                                    if (fc.fc is TcpFcc || fc.fc is UdpFcc) {
                                        Column(
                                            modifier = Modifier.fillMaxHeight() .fillMaxWidth(0.3f)
                                        ) {
                                            Icon(
                                                imageVector = Icons.Outlined.ArrowForward,
                                                contentDescription = "转向",
                                            )
                                        }
                                    }
                                    if (fc.fc is TcpFcc) {
                                        Column(
                                            modifier = Modifier.fillMaxHeight() .fillMaxWidth(0.3f),
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Icon(
                                                imageVector = Icons.Outlined.Circle,
                                                contentDescription = "服务端端口",
                                            )
                                            Text(
                                                modifier = Modifier.fillMaxWidth(),
                                                text = fc.fc.remotePort?.toString() ?: "",
                                                style = MaterialTheme.typography.titleSmall,
                                                textAlign = TextAlign.Center
                                            )
                                        }

                                    }
                                    if (fc.fc is UdpFcc) {
                                        Column(
                                            modifier = Modifier.fillMaxHeight() .fillMaxWidth(0.3f),
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Icon(
                                                imageVector = Icons.Outlined.Circle,
                                                contentDescription = "服务端端口",
                                            )
                                            Text(
                                                modifier = Modifier.fillMaxWidth(),
                                                text = fc.fc.remotePort?.toString() ?: "",
                                                style = MaterialTheme.typography.titleSmall,
                                                textAlign = TextAlign.Center
                                            )
                                        }
                                    }
                                }
                            }
                            Divider(modifier= Modifier.width(5.dp).heightIn(min = 40.dp) )
                            Card(
                                modifier=Modifier.fillMaxWidth(0.5f).padding(start = 10.dp,end = 10.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        modifier = Modifier.padding(3.dp) ,
                                        imageVector = Icons.Outlined.Dns,
                                        contentDescription = "中间服务商",
                                    )
                                    Column  {
                                        Text(
//                                            modifier = Modifier.fillMaxWidth(),
                                            text = fc.fc.host?:"",
                                            style = MaterialTheme.typography.titleSmall,
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
//                                            modifier = Modifier.fillMaxWidth(),
                                            text = fc.fc.port?.toString()?:"",
                                            style = MaterialTheme.typography.titleSmall,
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                }
                            }
                            
                            if(fc.fc is XtcpFcc){
                                Divider(modifier= Modifier.width(5.dp).heightIn(min = 40.dp) )
                                Card(
                                    modifier=Modifier.fillMaxWidth(0.5f).padding(start = 10.dp,end = 10.dp)
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        if( TcpFcc.W_S_S ==  fc.fc.side ){
                                            Icon(
                                                modifier = Modifier.padding(3.dp) ,
                                                imageVector = Icons.Outlined.SettingsInputSvideo,
                                                contentDescription = "服务端",
                                            )
                                        }else{
                                            Icon(
                                                modifier = Modifier.padding(3.dp) ,
                                                imageVector = Icons.Outlined.SettingsInputHdmi,
                                                contentDescription = "客户端",
                                            )
                                        }
                                    }
                                }

                            }
                            
                            if( fc.fc is StcpFcc ){
                                Divider(modifier= Modifier.width(5.dp).heightIn(min = 40.dp) )
                                Card(
                                    modifier=Modifier.fillMaxWidth(0.5f).padding(start = 10.dp,end = 10.dp)
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        if( TcpFcc.W_S_S ==  fc.fc.side ){
                                            Icon(
                                                modifier = Modifier.padding(3.dp) ,
                                                imageVector = Icons.Outlined.SettingsInputSvideo,
                                                contentDescription = "服务端",
                                            )
                                        }else{
                                            Icon(
                                                modifier = Modifier.padding(3.dp) ,
                                                imageVector = Icons.Outlined.SettingsInputHdmi,
                                                contentDescription = "客户端",
                                            )
                                        }
                                    }
                                }

                            }
                            
                        }
                           
                    }
                    
//                    后
                    Column(modifier = Modifier , /*.weight(0.5f),*/
                        horizontalAlignment = Alignment.End
                    ) {
                         Row(
                            /* modifier = Modifier.background(color = Color.Red),*/
                             verticalAlignment = Alignment.CenterVertically
                         ) {
                             Column(
                                 verticalArrangement = Arrangement.Center,
                                 horizontalAlignment = Alignment.CenterHorizontally
                             ){
                                 Checkbox(
                                     modifier = Modifier.padding(0.dp),
                                     checked = ifRunOnly,
                                     enabled = false,
                                     onCheckedChange = {
                                         fc.ifrunOnly = it
                                         ifRunOnly = it 
                                     }
                                 )
                                 Text(
                                     "独立运行",
                                     style = MaterialTheme.typography.titleSmall,
                                     textAlign = TextAlign.Center
                                 
                                 )
                             }

                             Spacer(Modifier.width(16.dp))
                             Column(
                                 verticalArrangement = Arrangement.Center,
                                 horizontalAlignment = Alignment.CenterHorizontally
                             ){
                                 Checkbox(
                                     modifier = Modifier.padding(0.dp),
                                     checked = enabled,
                                     enabled = true,
                                     onCheckedChange = {
                                         fc.fc.enabled = it
                                         enabled = it
                                     }
                                 )
                                 Text(
                                     "自启",
                                     style = MaterialTheme.typography.titleSmall,
                                     textAlign = TextAlign.Center
                                 )
                             }
                             
                             Spacer(Modifier.width(16.dp))
                             IconButton(
                                 colors = IconButtonDefaults.iconButtonColors(
                                     containerColor  = Color(0xFFFFD8E4),
                                 ),
                                 onClick = {
                                     if(fc.ifrunnig.value){
                                         alert("正在运行中，请先停止")
                                     }else{
                                         removeFc(fc)
                                     }
                                     
                                 }
                             ) {
                                 Icon(
                                     imageVector = Icons.Outlined.Delete,
                                     contentDescription = "删除",
                                 )
                             }
                             Spacer(Modifier.width(16.dp))
                             if(loadding){
                                 Icon(
                                     imageVector = Icons.Outlined.Sync,
                                     contentDescription = "正在加载中",
                                 )
                             }else{
                                 Switch(
                                     checked = fc.ifrunnig.value,
                                     onCheckedChange = {
                                         fc.expend.value  = true
                                         run.launch { 
                                             delay(300)
                                             if (!formState.virfyAll()){
                                                 alert("无法启动，请确保参数填写正常！")
                                                 this.cancel()
                                             }
                                             loadding = true
                                             if(it){
                                                 //开启
                                                 startFrpC(fc)
                                                 run.launch {
                                                     if(fc.ec != null && fc.ec?.executeWatchdog !=null && fc.ec?.executeWatchdog?.isWatching == true){
                                                         alert("启动成功！")
                                                     }else {
                                                         alert("启动失败！")
                                                     }
                                                 }
                                             }else{
                                                 //关闭
                                                 stopFrpC(fc)
                                                 alert("关闭完毕！")
                                             }
                                             loadding = false
                                         }
                                         
                                     }
                                 )
                             }
                             
                             Spacer(Modifier.width(16.dp))
                             // 下拉按钮
                             IconButton(onClick = {
                                 onExpand()
                                 fc.expend.value = fc.expend.value.not()
                             }) {
                                 Icon(
                                     imageVector = if (fc.expend.value) {
                                         Icons.Rounded.KeyboardArrowUp
                                     } else {
                                         Icons.Rounded.KeyboardArrowDown
                                     },
                                     contentDescription = null,
                                 )
                             }
                         }
                    }
                }
            AnimatedVisibility(fc.expend.value) {
                Column {
                    Spacer(modifier = Modifier.height(8.dp))
                    content(fc)
                }
            }
        }
    }
}