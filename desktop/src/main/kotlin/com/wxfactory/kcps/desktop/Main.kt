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
fun main() {
    runBlocking {
        koin = startKoin {
            // 默认往这个上下文中 注入两个模块
            modules(
                listOf(
                    common,
                    moudleForPf()
                ),
            )
        }.koin
//        val fakes = koin.get<MutableList<FrpConfigCCompose<FrpConfigC>>>(named("fcs"))

//        repeat(4) { time ->
//            delay(500)
//            fakes.add(FrpConfigCCompose(TcpFcc("localhost", time).apply {
//                this.name = "测试$time"
//                this.localIP = "localhost$time"
//                this.localPort = time
//            }))
//        }
//        delay(500)
//        fakes.add(FrpConfigCCompose(XtcpFcc("localhost", 234).apply {
//            this.name = "XTCP测试321"
//            this.localIP = "localhost"
//            this.localPort = 993
//        }))
//        delay(500)
//        fakes.add(FrpConfigCCompose(TcpmuxFcc("localhost", 234).apply {
//            this.name = "XTCP测试321"
//            this.localIP = "localhost"
//            this.multiplexer = "what"
//            this.customDomains = "wxfactory.com"
//        }))

        application {
            Window(
                onCloseRequest = { exitApplication() },
                title = "kcps",
                state = rememberWindowState(
                    position = WindowPosition.Aligned(Alignment.Center),
                    width = 1100.dp,
                    height = 700.dp,
                ),
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Main()
                }
            }
        }
    }
}