package com.wxfactory.kcps.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.wxfactory.kcps.common.screen.data.ScreenViewModel
import org.koin.compose.KoinContext
import org.koin.compose.koinInject


@Composable
fun Main(
    mainViewModel: ScreenViewModel = koinInject<ScreenViewModel>() ,
) {
    val darkTheme = when (mainViewModel.appTheme.collectAsState().value) {
        1 -> true
        else -> false
    }
    
    //表示指定koin上下文，默认也是GlobalContext，感觉要不要都无所谓
    KoinContext{
        
    }
}
