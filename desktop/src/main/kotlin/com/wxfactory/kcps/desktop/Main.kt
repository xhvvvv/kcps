package com.wxfactory.kcps.desktop

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import org.koin.core.Koin
import org.koin.core.context.startKoin
import com.wxfactory.kcps.common.Main
import com.wxfactory.kcps.common.core.entity.FrpConfigCCompose
import com.wxfactory.kcps.common.core.moudle.common
import com.wxfactory.kcps.common.platform.moudleForPf
import com.wxfactory.kcps.frpfun.entity.FrpConfig
import com.wxfactory.kcps.frpfun.entity.FrpConfigC
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.TcpFcc
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.TcpmuxFcc
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.XtcpFcc
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.koin.core.qualifier.named
import org.koin.dsl.module

lateinit var koin: Koin
fun main() = application {
    koin = startKoin {
        // 默认往这个上下文中 注入两个模块
        modules(
            listOf(
                common,
                moudleForPf()
            ),
        )
    }.koin
    val icon = painterResource("icon.svg")
    Tray(
        icon = icon,
        menu = {
            Item("关闭连接并退出", onClick = ::exitApplication)
        }
    )
    Window(
        onCloseRequest = { exitApplication() },
        title = "kcps",
        state = rememberWindowState(
            position = WindowPosition.Aligned(Alignment.Center),
            width = 1100.dp,
            height = 700.dp,
        ),
        icon = icon
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Main()
        }
    }
}