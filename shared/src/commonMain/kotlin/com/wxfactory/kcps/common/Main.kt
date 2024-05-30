package com.wxfactory.kcps.common

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.AlertDialog
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import com.wxfactory.kcps.common.core.entity.FrpConfigCCompose
import com.wxfactory.kcps.common.core.moudle.FC_C
import com.wxfactory.kcps.common.core.moudle.FC_S
import com.wxfactory.kcps.common.platform.frpfun.startFrp
import com.wxfactory.kcps.common.platform.frpfun.stopFrp
import com.wxfactory.kcps.common.public.AlertDialogDefaults
import com.wxfactory.kcps.common.public.FormState
import com.wxfactory.kcps.common.public.LocalFormState
import com.wxfactory.kcps.common.screen.ConfigPanel
import com.wxfactory.kcps.common.screen.data.ScreenViewModel
import com.wxfactory.kcps.common.screen.theme.DarkColors
import com.wxfactory.kcps.common.screen.theme.LightColors
import com.wxfactory.kcps.common.util.theme.MyMaterialTheme
import com.wxfactory.kcps.frpfun.entity.FrpConfig
import com.wxfactory.kcps.frpfun.entity.FrpConfigC
import com.wxfactory.kcps.frpfun.entity.FrpConfigS
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.withLock
import org.koin.compose.KoinContext
import org.koin.compose.koinInject
import org.koin.compose.rememberKoinInject
import org.koin.core.qualifier.named
import org.koin.java.KoinJavaComponent.inject


@Composable
fun Main(
    mainViewModel: ScreenViewModel = koinInject<ScreenViewModel>(),
) {
    val darkTheme = when (mainViewModel.appTheme.collectAsState().value) {
        1 -> true
        else -> false
    }
    MyMaterialTheme(
        darkTheme
    )  {
        AlertDialogDefaults{
            //这里等待数据加载完成后再初始化和
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background,
            ) {
                //主界面，这里只有一个界面
                Navigator(
                    screen =  ConfigPanel(items = koinInject(named("tabs"))),
                )

            }
        }
        
    }
    
    //查看服务端是否需要启动
    LaunchedEffect(Unit){
        val fcs  by inject<FrpConfigCCompose<FrpConfigS>> (FrpConfigCCompose::class.java,named("fcServer"))
        try {
            fcs.mutex.withLock {
                fcs.ifLoading.value = true
                //是否自启
                if (fcs.ifStartOnce.not() && fcs.fc.enabled) {
                    fcs.ifStartOnce = true
                    //启动它 
                    //注册回调
                    fcs.exeStartCallBack = {
                        fcs.ifrunnig.value = true
                    }
                    fcs.exeCallBack = {
                        fcs.ifrunnig.value = false
                    }
                    val con = startFrp(fcs as FrpConfigCCompose<FrpConfig>)
                    if (con.isExcuteSuccess()) {
                        //开启
                        fcs.ifrunnig.value = true
                    }
                }
                
            }
        }finally {
            fcs.ifLoading.value = false
        }
    }
    DisposableEffect(Unit) {
        //保存数据
        onDispose {
            val mff  by inject<MutableList<FrpConfigCCompose<FrpConfigC>>> (MutableList::class.java,named(FC_C))
            val fcs  by inject<FrpConfigCCompose<FrpConfigS>> (FrpConfigCCompose::class.java,named(FC_S))
            val fccs = mff.map { it.fc }
            mainViewModel.save(fccs)
            mainViewModel.saveFcsServer(fcs.fc)
        }
         
    }
}
