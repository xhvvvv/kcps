package com.wxfactory.kcps.common

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import com.wxfactory.kcps.common.core.entity.FrpConfigCCompose
import com.wxfactory.kcps.common.screen.ConfigPanel
import com.wxfactory.kcps.common.screen.data.ScreenViewModel
import com.wxfactory.kcps.common.screen.theme.DarkColors
import com.wxfactory.kcps.common.screen.theme.LightColors
import com.wxfactory.kcps.frpfun.entity.FrpConfigC
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
    val allTabs = remember {  }
//    //表示指定koin上下文，默认也是GlobalContext，感觉要不要都无所谓
//    KoinContext{}
    val autoColors = if (darkTheme) DarkColors else LightColors
    
    MaterialTheme(
        colorScheme = autoColors,
    ) {
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

    DisposableEffect(Unit) {
        //保存数据
        onDispose {
            println("保存")
            val mff  by inject<MutableList<FrpConfigCCompose<FrpConfigC>>> (MutableList::class.java,named("fcs"))
            val fccs = mff.map { it.fc }
            mainViewModel.save(fccs)
        }
         
    }
}