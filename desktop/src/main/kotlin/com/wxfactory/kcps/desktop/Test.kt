package com.wxfactory.kcps.desktop

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
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
import com.wxfactory.kcps.common.core.moudle.common
import com.wxfactory.kcps.common.platform.moudleForPf
import com.wxfactory.kcps.frpfun.entity.FrpConfig
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.XtcpFcc
import org.koin.core.qualifier.named
import org.koin.dsl.module

 
fun main() {
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
                Content()
            }
        }
    }
}
@Composable
private fun Content() {
    var number by remember { mutableStateOf(0) }
    // 用户信息
    val user = remember { User() }

    Column {
        Text(
            text = number.toString(),
            modifier = Modifier
                .clickable {
                    number++
                }
                .padding(10.dp)
        )

        // 用户信息
        UserInfo(user = user)
    }
}

@Composable
private fun UserInfo(user: User) {
    println("chongiz ")
    Text(text = user.name)
}
//@Immutable

@Stable
 class User(
    val name: String = "default" ,
    var shit: String = "3232" ,
)