package com.wxfactory.kcps.common.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.screenModelScope
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cn.hutool.core.util.StrUtil
import com.wxfactory.kcps.common.core.entity.FrpConfigCCompose
import com.wxfactory.kcps.common.platform.frpfun.startFrp
import com.wxfactory.kcps.common.platform.frpfun.stopFrp
import com.wxfactory.kcps.common.public.*
import com.wxfactory.kcps.common.public.validate.NnullValidator
import com.wxfactory.kcps.common.public.validate.intValidator
import com.wxfactory.kcps.common.public.validate.nnullValidator
import com.wxfactory.kcps.common.screen.data.ScreenViewModel
import com.wxfactory.kcps.common.util.i18N
import com.wxfactory.kcps.frpfun.entity.Authentication
import com.wxfactory.kcps.frpfun.entity.FrpConfig
import com.wxfactory.kcps.frpfun.entity.FrpConfigS
import com.wxfactory.kcps.frpfun.entity.auths.MethodType
import com.wxfactory.kcps.frpfun.entity.auths.TokenAuth
import com.wxfactory.kcps.frpfun.util.CommonUtil
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.withLock
import org.koin.compose.koinInject
import org.koin.core.qualifier.named
import java.util.*
import com.wxfactory.kcps.common.public.CheckBox as MyCheckBox

class ServerTab() : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = "服务端"
            val imageVector = rememberVectorPainter(Icons.Outlined.Dns)
            return TabOptions(
                index = 1u,
                title = title,
                icon = imageVector,
            )
        }

    @Composable
    override fun Content() {
        ServerSideScreen()
    }

}


@Composable
fun ServerSideScreen(
    mainViewModel: ScreenViewModel? = koinInject<ScreenViewModel>(),
    fcs: FrpConfigCCompose<FrpConfigS> = koinInject<FrpConfigCCompose<FrpConfigS>>(named("fcServer")),
) {
    val fontType = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold)
    val snackbarHostState = remember { SnackbarHostState() }
    val run = rememberCoroutineScope()
    val alert : (String) -> Unit = remember {
        {
            run.launch {
                run.launch {
                    snackbarHostState.showSnackbar( it )
                }
                run.launch {
                    delay(1000)
                    snackbarHostState.currentSnackbarData?.dismiss()
                }
            }
        }
    }

    Form {
        val formState = LocalFormState.current
        LaunchedEffect(Unit) {
            //注册校验器
            formState {
                "port" useValidators listOf(
                    nnullValidator, intValidator
                )
                "kcpBindPort" useValidators listOf(
                    intValidator
                )
                "quicBindPort" useValidators listOf(
                    intValidator
                )
                "tcpmuxHTTPConnectPort" useValidators listOf(
                    intValidator
                )
                "pass" useValidators listOf(
                    nnullValidator
                )
                "maxPortsPerClient" useValidators listOf(
                    nnullValidator,
                    intValidator
                )
                "vhostHTTPPort" useValidators listOf(
                    intValidator
                )
                "vhostHTTPSPort" useValidators listOf(
                    intValidator
                )
                "vhostHTTPTimeout" useValidators listOf(
                    intValidator
                )
            }
        }
        
        Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            },
            bottomBar = {
                //启动和关闭配置的逻辑
                val runLogic :  ( Boolean) -> Unit = remember(Unit) {
                    //注册回调
                    fcs.exeStartCallBack = {
                        fcs.ifrunnig.value = true
                    }
                    fcs.exeCallBack = {
                        fcs.ifrunnig.value = false
                    }

                    { it ->
                        run.launch {
                            fcs.ifLoading.value = true
                            try {
                                fcs.mutex.withLock {
                                    if (fcs.fc.id.isNullOrBlank()){
                                        fcs.fc.id = CommonUtil.generateId()
                                    }
                                    if (!formState.virfyAll()) {
                                        alert("无法启动，请确保参数填写正常！")
                                        this.cancel()
                                        return@launch
                                    }
                                    if (it) {
                                        val con = startFrp(fcs as FrpConfigCCompose<FrpConfig>)
                                        if (con.isExcuteSuccess()) {
                                            //开启
                                            alert("启动成功！")
                                        } else {
                                            alert(con.remark ?: "启动失败！")
                                        }
                                    } else {
                                        //关闭
                                        stopFrp(fcs as FrpConfigCCompose<FrpConfig>)
                                        alert("关闭完毕！")
                                    }
                                }
                            }finally {
                                fcs.ifLoading.value = false
                            }
                        }

                    }
                }

                BottomAppBar(
                    floatingActionButton = {
                        FloatingActionButton(
                            modifier = Modifier.size(50.dp,50.dp),
                            onClick = {
                                runLogic(fcs.getIfBusy().not())
                            },
                            containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                            elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                        ) {
                            if(fcs.ifrunnig.value){
                                Icon(
                                    modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primary),
                                    imageVector = Icons.Filled.PauseCircleOutline,
                                    contentDescription ="关闭")
                            }else{
                                Icon(
                                    modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.error),
                                    imageVector =  Icons.Filled.PlayCircleOutline, 
                                    contentDescription = "启动"
                                )
                            }
                        }
                    },
                    actions = {
                        
                    }
                )
            }
        ) { innerPadding ->
            val scrollState = rememberScrollState()
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(scrollState),
                shape =  RoundedCornerShape(0.dp),
            ) {

                //第一行
                MyServerRow {
                    InputNo1(
                        id = "name",
                        modifier = Modifier.width(200.dp),
                        title = i18N.getProperty("cg-name"),
                        type = KeyboardType.Text,
                        currentValue = fcs.fc.name ?: "",
                        onValueChange = {
                            fcs.fc.name = it ?: ""
                        },
                        editable = fcs.getIfBusy().not()
                    )

                    InputNo1(
                        id = "host",
                        modifier = Modifier.width(200.dp),
                        title = i18N.getProperty("cg-localIP"),
                        type = KeyboardType.Text,
                        currentValue = fcs.fc.host ?: "",
                        onValueChange = {
                            fcs.fc.host = it ?: ""
                        },
                        editable = fcs.getIfBusy().not()
                    )

                    InputNo1(
                        id = "port",
                        modifier = Modifier.width(200.dp),
                        title = i18N.getProperty("cg-localPort"),
                        type = KeyboardType.Number,
                        currentValue = fcs.fc.port?.toString() ?: "",
                        onValueChange = {
                            if(it == null || it == ""){
                                fcs.fc.port = null
                            }else{
                                fcs.fc.port = it.toInt()
                            }
                        },
                        editable = fcs.getIfBusy().not()
                    )

                    InputNo1(
                        id = "maxPortsPerClient",
                        modifier = Modifier.width(200.dp),
                        title = i18N.getProperty("cg-maxPortsPerClient"),
                        type = KeyboardType.Number,
                        currentValue = fcs.fc.maxPortsPerClient?.toString() ?: "",
                        onValueChange = {
                            if(it == null || it == ""){
                                fcs.fc.maxPortsPerClient = null
                            }else{
                                fcs.fc.maxPortsPerClient = it.toInt()
                            }
                        },
                        editable = fcs.getIfBusy().not(),
                        placeholder = "0为不限制"
                    )
                    MyCheckBox(
                        modifier = Modifier.width(width = 50.dp ),
                        currentValue = fcs.fc.enabled,
                        onValueChange = {
                            fcs.fc.enabled = it
                            fcs.ifStartOnce = true // 从下次开始自启
                        }
                    ){
                        Text(
                            style = fontType,
                            text = "自启",
                            textAlign = TextAlign.Center
                        )
                    }
                }
                //第二行
                MyServerRow{
                    val authType = remember { MethodType.values().toList() }
                    var chosedAuthType by remember { mutableStateOf(authType[0]) }
                    Select<MethodType>(
                        modifier = Modifier.width(200.dp),
                        options = authType,
                        enabled = fcs.getIfBusy().not(),
                        selectedOptionShow = { it.name },
                        optionSelectedShow = { it.name },
                        onOptionSelected = { type -> chosedAuthType = type }
                    ) {
                        Text(
                            style = fontType,
                            text = "认证方式"
                        )
                    }

                    when (chosedAuthType) {
                        MethodType.TOKEN -> {
                            if (fcs.fc.authentication == null) {
                                fcs.fc.authentication = TokenAuth("")
                            }
                            InputNo1(
                                id = "pass",
                                modifier = Modifier.width(200.dp),
                                title = "密匙",
                                type = KeyboardType.Password,
                                currentValue = (fcs.fc.authentication as TokenAuth).token ?: "",
                                onValueChange = {
                                    (fcs.fc.authentication as TokenAuth).token = it ?: ""
                                },
                                editable = fcs.getIfBusy().not()
                            )
                        }

                        MethodType.OIDC -> TODO()
                    }
                }
                //第三行
                MyServerRow{
                    InputNo1(
                        id = "vhostHTTPPort",
                        modifier = Modifier.width(200.dp),
                        title = i18N.getProperty("cg-vhostHTTPPort"),
                        type = KeyboardType.Text,
                        currentValue = fcs.fc.vhostHTTPPort?.toString() ?: "",
                        onValueChange = {
                            if(it == null || it == ""){
                                fcs.fc.vhostHTTPPort = null
                            }else{
                                fcs.fc.vhostHTTPPort = it.toInt()
                            }
                        },
                        editable = fcs.getIfBusy().not()
                    )
                    InputNo1(
                        id = "vhostHTTPSPort",
                        modifier = Modifier.width(200.dp),
                        title = i18N.getProperty("cg-vhostHTTPSPort"),
                        type = KeyboardType.Text,
                        currentValue = fcs.fc.vhostHTTPSPort?.toString() ?: "",
                        onValueChange = {
                            if(it == null || it == ""){
                                fcs.fc.vhostHTTPSPort = null
                            }else{
                                fcs.fc.vhostHTTPSPort = it.toInt()
                            }
                        },
                        editable = fcs.getIfBusy().not()
                    )
                    InputNo1(
                        id = "vhostHTTPTimeout",
                        modifier = Modifier.width(200.dp),
                        title = i18N.getProperty("cg-vhostHTTPTimeout"),
                        type = KeyboardType.Text,
                        currentValue = fcs.fc.vhostHTTPTimeout?.toString() ?: "",
                        onValueChange = {
                            if(it == null || it == ""){
                                fcs.fc.vhostHTTPTimeout = null
                            }else{
                                fcs.fc.vhostHTTPTimeout = it.toInt()
                            }
                        },
                        editable = fcs.getIfBusy().not()
                    )
                }

                //第四行
                MyServerRow{
                    InputNo1(
                        id = "kcpBindPort",
                        modifier = Modifier.width(200.dp),
                        title = i18N.getProperty("cg-kcpBindPort"),
                        type = KeyboardType.Text,
                        currentValue = fcs.fc.kcpBindPort?.toString() ?: "",
                        onValueChange = {
                            if(it == null || it == ""){
                                fcs.fc.kcpBindPort = null
                            }else{
                                fcs.fc.kcpBindPort = it.toInt()
                            }
                        },
                        editable = fcs.getIfBusy().not()
                    )
                    InputNo1(
                        id = "quicBindPort",
                        modifier = Modifier.width(200.dp),
                        title = i18N.getProperty("cg-quicBindPort"),
                        type = KeyboardType.Text,
                        currentValue = fcs.fc.quicBindPort?.toString() ?: "",
                        onValueChange = {
                            if(it == null || it == ""){
                                fcs.fc.quicBindPort = null
                            }else{
                                fcs.fc.quicBindPort = it.toInt()
                            }
                        },
                        editable = fcs.getIfBusy().not()
                    )
                    InputNo1(
                        id = "tcpmuxHTTPConnectPort",
                        modifier = Modifier.width(200.dp),
                        title = i18N.getProperty("cg-tcpmuxHTTPConnectPort"),
                        type = KeyboardType.Number,
                        currentValue = fcs.fc.tcpmuxHTTPConnectPort?.toString() ?: "",
                        onValueChange = {
                            if(it == null || it == ""){
                                fcs.fc.tcpmuxHTTPConnectPort = null
                            }else{
                                fcs.fc.tcpmuxHTTPConnectPort = it.toInt()
                            }
                        },
                        editable = fcs.getIfBusy().not()
                    )
                    MyCheckBox(
                        modifier = Modifier.width(width = 50.dp ),
                        currentValue =fcs.fc.tcpmuxPassthrough,
                        onValueChange = {
                            fcs.fc.tcpmuxPassthrough = it
                        }
                    ){
                        Text(
                            style = fontType,
                            text = i18N.getProperty("cg-tcpmuxPassthrough"),
                            textAlign = TextAlign.Center
                        )
                    }
                    MyCheckBox(
                        modifier = Modifier.width(width = 50.dp ),
                        currentValue =fcs.fc.detailedErrorsToClient,
                        onValueChange = {
                            fcs.fc.detailedErrorsToClient = it
                        }
                    ){
                        Text(
                            style = fontType,
                            text = i18N.getProperty("cg-detailedErrorsToClient"),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

        }
    }

} 



@Composable
private fun MyServerRow(
    content: (@Composable () -> Unit) 
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        content()
    }
}