package com.wxfactory.kcps.common

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.navigator.Navigator
import com.wxfactory.kcps.common.screen.HelloScreen
import com.wxfactory.kcps.common.screen.data.ScreenViewModel
import com.wxfactory.kcps.common.screen.theme.DarkColors
import com.wxfactory.kcps.common.screen.theme.LightColors
import org.koin.compose.KoinContext
import org.koin.compose.koinInject


@Composable
fun Main(
    mainViewModel: ScreenViewModel = koinInject<ScreenViewModel>(),
) {
    val darkTheme = when (mainViewModel.appTheme.collectAsState().value) {
        1 -> true
        else -> false
    }

//    //表示指定koin上下文，默认也是GlobalContext，感觉要不要都无所谓
//    KoinContext{}
    val autoColors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = autoColors
    ) {
        //这里等待数据加载完成后再初始化和
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            
            //主界面
            Navigator(
                screen =  HelloScreen(),
            )
        }

    }
}
