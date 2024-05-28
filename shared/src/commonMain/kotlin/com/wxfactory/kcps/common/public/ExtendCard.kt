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
import com.wxfactory.kcps.common.platform.frpfun.startFrp
import com.wxfactory.kcps.common.platform.frpfun.stopFrp
import com.wxfactory.kcps.frpfun.entity.FrpConfig
import com.wxfactory.kcps.frpfun.entity.FrpConfigC
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.StcpFcc
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.TcpFcc
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.UdpFcc
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.XtcpFcc
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.withLock

/*统一图标的大小*/   
private fun Modifier.fecIconSize() =  this.size(15.dp,20.dp)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun fccExtendCard(
    index : Int ,
    fc: FrpConfigCCompose<FrpConfigC>,
    modifier: Modifier = Modifier,
    removeFc : (FrpConfigCCompose<FrpConfigC>) ->Unit,
    onExpand: () -> Unit, //切换展开时的调用
    alert : (String) -> Unit,
    content: @Composable ( fc: FrpConfigCCompose<FrpConfigC> ) -> Unit,
) {
   
    Card(
        modifier = modifier,
        onClick = {
            onExpand()
            fc.expend.value = fc.expend.value.not()
        },
    ) {
        Column(
            modifier = modifier.padding(3.dp),
            verticalArrangement = Arrangement.Center
        ) {
            // 展示信息
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(65.dp)
                ,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                //前
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(0.1f)
                ) {

                    Text(
                        text = index.toString(),
                        style = MaterialTheme.typography.titleMedium,
                    )

                    Row(
                         modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    )  {
                        
                        Icon(
                            modifier = Modifier.fecIconSize(),
                            imageVector = Icons.Outlined.HourglassEmpty,
                            contentDescription = "配置名称",
                        )
                        Text(
                            text = fc.fc.name?:"",
                            style = MaterialTheme.typography.bodySmall,
                        )
                    }
                }
                Divider(modifier= Modifier.width(2.dp).fillMaxHeight())
                //中
                Column(
                    modifier = Modifier 
                    .fillMaxHeight()
                    .fillMaxWidth(0.7f)
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                    ) {
                        Card(
                            modifier=Modifier.fillMaxWidth(0.2f).padding(start = 10.dp,end = 10.dp)
                        ) {
                            Icon(
                                modifier = Modifier.align(Alignment.CenterHorizontally).fecIconSize(),
                                imageVector = Icons.Outlined.Category,
                                contentDescription = "协议类型",
                            )
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = fc.fc.type,
                                style = MaterialTheme.typography.bodySmall,
                                textAlign = TextAlign.Center
                            )
                        }
                        Divider(modifier= Modifier.width(2.dp).fillMaxHeight())
                        Card(
                            modifier=Modifier.fillMaxWidth(0.5f).padding(start = 10.dp,end = 10.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxSize(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxHeight().fillMaxWidth(0.3f),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Icon(
                                        modifier = Modifier.fecIconSize(),
                                        imageVector = Icons.Outlined.Circle,
                                        contentDescription = "占用端口",
                                    )
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = fc.fc.localPort?.toString() ?: "",
                                        style = MaterialTheme.typography.bodySmall,
                                        textAlign = TextAlign.Center
                                    )
                                }

                                if (fc.fc is TcpFcc || fc.fc is UdpFcc) {
                                    Column(
                                        modifier = Modifier.fillMaxHeight() .fillMaxWidth(0.3f),
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Icon(
                                            modifier = Modifier.fecIconSize(),
                                            imageVector = Icons.Outlined.ArrowForward,
                                            contentDescription = "转向",
                                        )
                                    }
                                }
                                if (fc.fc is TcpFcc) {
                                    Column(
                                        modifier = Modifier.fillMaxHeight() .fillMaxWidth(0.3f),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Icon(
                                            modifier = Modifier.fecIconSize(),
                                            imageVector = Icons.Outlined.Circle,
                                            contentDescription = "服务端端口",
                                        )
                                        Text(
                                            modifier = Modifier.fillMaxWidth(),
                                            text = fc.fc.remotePort?.toString() ?: "",
                                            style = MaterialTheme.typography.bodySmall,
                                            textAlign = TextAlign.Center
                                        )
                                    }

                                }
                                if (fc.fc is UdpFcc) {
                                    Column(
                                        modifier = Modifier.fillMaxHeight() .fillMaxWidth(0.3f),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Icon(
                                            modifier = Modifier.fecIconSize(),
                                            imageVector = Icons.Outlined.Circle,
                                            contentDescription = "服务端端口",
                                        )
                                        Text(
                                            modifier = Modifier.fillMaxWidth(),
                                            text = fc.fc.remotePort?.toString() ?: "",
                                            style = MaterialTheme.typography.bodySmall,
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                }
                            }
                        }
                        Divider(modifier= Modifier.width(2.dp).fillMaxHeight())
                        Card(
                            modifier=Modifier.fillMaxWidth(0.4f).padding(start = 10.dp,end = 10.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    modifier = Modifier.padding(3.dp).fecIconSize() ,
                                    imageVector = Icons.Outlined.Dns,
                                    contentDescription = "中间服务商",
                                )
                                Column  {
                                    Text(
//                                            modifier = Modifier.fillMaxWidth(),
                                        text = fc.fc.host?:"",
                                        style = MaterialTheme.typography.bodySmall,
                                        textAlign = TextAlign.Center
                                    )
                                    Text(
//                                            modifier = Modifier.fillMaxWidth(),
                                        text = fc.fc.port?.toString()?:"",
                                        style = MaterialTheme.typography.bodySmall,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                        
                        if(fc.fc is XtcpFcc){
                            Divider(modifier= Modifier.width(2.dp).fillMaxHeight())
                            Card(
                                modifier=Modifier.fillMaxWidth(0.5f).padding(start = 10.dp,end = 10.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    if( TcpFcc.W_S_S ==  fc.fc.side ){
                                        Icon(
                                            modifier = Modifier.padding(3.dp).fecIconSize() ,
                                            imageVector = Icons.Outlined.SettingsInputSvideo,
                                            contentDescription = "服务端",
                                        )
                                    }else{
                                        Icon(
                                            modifier = Modifier.padding(3.dp).fecIconSize() ,
                                            imageVector = Icons.Outlined.SettingsInputHdmi,
                                            contentDescription = "客户端",
                                        )
                                    }
                                }
                            }

                        }
                        
                        if( fc.fc is StcpFcc ){
                            Divider(modifier= Modifier.width(2.dp).fillMaxHeight())
                            Card(
                                modifier=Modifier.fillMaxWidth(0.5f).padding(start = 10.dp,end = 10.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    if( TcpFcc.W_S_S ==  fc.fc.side ){
                                        Icon(
                                            modifier = Modifier.padding(3.dp).fecIconSize() ,
                                            imageVector = Icons.Outlined.SettingsInputSvideo,
                                            contentDescription = "服务端",
                                        )
                                    }else{
                                        Icon(
                                            modifier = Modifier.padding(3.dp).fecIconSize() ,
                                            imageVector = Icons.Outlined.SettingsInputHdmi,
                                            contentDescription = "客户端",
                                        )
                                    }
                                }
                            }

                        }
                        
                    }
                       
                }
                
//              后
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                    , 
                    horizontalAlignment = Alignment.End
                ) {
                    operationMain(fc , removeFc  , onExpand , alert ,)
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

/**
 * 具有操控的部分
 */
@Composable
fun operationMain(
    fc: FrpConfigCCompose<FrpConfigC>,
    removeFc : (FrpConfigCCompose<FrpConfigC>) ->Unit,
    onExpand: () -> Unit, //切换展开时的调用
    alert : (String) -> Unit,
){
    val run = rememberCoroutineScope()
    var ifRunOnly : Boolean by mutableStateOf(fc.ifrunOnly )
    var enabled : Boolean by remember { mutableStateOf(fc.fc.enabled )}
    val formState = LocalFormState.current
    //启动和关闭配置的逻辑
    val runLogic :  ( Boolean) -> Unit = remember {
        //注册回调
        fc.exeStartCallBack = {
            fc.ifrunnig.value = true
        }
        fc.exeCallBack = {
            fc.ifrunnig.value = false
        }

        {  it ->
            fc.expend.value  = true
            fc.ifLoading.value = true
            run.launch {
                try {
                    fc.mutex.withLock {
                        delay(300)
                        if (!formState.virfyAll()) {
                            alert("无法启动，请确保参数填写正常！")
                            this.cancel()
                            return@launch
                        }
                        if (it) {
                            val con = startFrp(fc as FrpConfigCCompose<FrpConfig>)
                            if (con.isExcuteSuccess()) {
                                //开启
                                fc.ifrunnig.value = it
                                alert("启动成功！")
                            } else {
                                alert(con.remark ?: "启动失败！")
                            }
                        } else {
                            //关闭
                            stopFrp(fc as FrpConfigCCompose<FrpConfig>)
                            alert("关闭完毕！")
                        }
                    }
                }finally {
                    fc.ifLoading.value = false
                }
                
            }
        }
    }
    LaunchedEffect(Unit){
        //是否自启
        if (fc.ifStartOnce.not() && fc.fc.enabled){
            fc.ifStartOnce = true
            runLogic(true)
        }
    }

    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Checkbox(
                modifier = Modifier.size(width = 20.dp, height =35.dp),
                checked = ifRunOnly,
                enabled = false,
                onCheckedChange = {
                    fc.ifrunOnly = it
                    ifRunOnly = it
                }
            )
            Text(
                "独立运行",
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center

            )
        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Checkbox(
                modifier = Modifier.size(width = 20.dp, height =35.dp),
                checked = enabled,
                enabled = true,
                onCheckedChange = {
                    fc.fc.enabled = it
                    enabled = it
                    fc.ifStartOnce = true // 从下次开始自启
                }
            )
            Text(
                "自启",
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center
            )
        }

        IconButton(
            colors = IconButtonDefaults.iconButtonColors(
                containerColor  = MaterialTheme.colorScheme.errorContainer,
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
                modifier = Modifier.size(25.dp,20.dp),
                imageVector = Icons.Outlined.Delete,
                contentDescription = "删除",
            )
        }
        if(fc.ifLoading.value){
            Icon(
                modifier = Modifier.size(20.dp,20.dp),
                imageVector = Icons.Outlined.Sync,
                contentDescription = "正在加载中",
            )
        }else{
            Switch(
                modifier = Modifier.fillMaxHeight().width(15.dp),
                checked = fc.ifrunnig.value,
                onCheckedChange = {
                    runLogic(it)
                }
            )
        }

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