package com.wxfactory.kcps.desktop

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import org.koin.core.Koin
import org.koin.core.context.startKoin

lateinit var koin: Koin
fun main() {
    koin = startKoin {
        // 默认往这个上下文中 注入两个模块
        modules(
            listOf(
                
            ),
        )
    }.koin
    return application {
        Window(
            onCloseRequest = { exitApplication() },
            title = "kcps",
            state = rememberWindowState(
                position = WindowPosition.Aligned(Alignment.Center),
                width = 1200.dp,
                height = 700.dp,
            ),
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
               Text("kcps启动")
            }
        }
    }
}
