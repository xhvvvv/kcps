package com.wxfactory.kcps.common.tabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FiberManualRecord
import androidx.compose.material.icons.outlined.Engineering
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.HourglassEmpty
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.wxfactory.kcps.common.public.InputNo1
import com.wxfactory.kcps.common.screen.data.ScreenViewModel
import com.wxfactory.kcps.common.util.i18N
import com.wxfactory.kcps.frpfun.entity.FrpConfig
import com.wxfactory.kcps.frpfun.entity.FrpConfigC
import org.koin.compose.koinInject
import org.koin.core.qualifier.named
import java.awt.SystemColor.text
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible


class AboutTab() : Tab{
    override val options: TabOptions
        @Composable
        get() {
            val title = i18N.getProperty("tab-aboutTab")
            val imageVector = rememberVectorPainter( Icons.Outlined.Engineering)
            return TabOptions(
                index = 7u,
                title = i18N.getProperty("tab-aboutTab"),
                icon = imageVector,
            )
        }
    @Composable
    override fun Content() {
        aboutPanel()
    }

}
 

@Composable
fun aboutPanel(
    mainViewModel: ScreenViewModel? = koinInject<ScreenViewModel>(),
) {
    Scaffold(){
        Card(
            modifier = Modifier.fillMaxSize().padding(it)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight(0.4f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.scale(0.6f) ,
                    painter = painterResource("image/wxfactory.jpg"),
                    contentDescription = "伟行工厂"
                )
                Text("伟行工厂制")
            }
            Column(
                modifier = Modifier
//                    .fillMaxHeight(0.3f)
                    .fillMaxWidth()
                    .padding(start = 50.dp, top = 15.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    "版本信息",
                            style =  MaterialTheme.typography.titleLarge
                )
                Divider(thickness=5.dp)
                
                Row {
                    Icon(
                        modifier = Modifier.scale(0.3f),
                        imageVector = Icons.Filled.FiberManualRecord,
                        contentDescription = "版本",
                    )
                    Text(
                        "0.0.1",
                    )
                }
                
                Row {
                    Icon(
                        modifier = Modifier.scale(0.3f),
                        imageVector = Icons.Filled.FiberManualRecord,
                        contentDescription = "版本说明",
                    )
                    Text(
                        "当前版本仅提供简单的使用",
                    )
                }
                
            }
           
            Column(
                modifier = Modifier
//                    .fillMaxHeight(0.3f)
                    .fillMaxWidth()
                    .padding(start = 50.dp, top = 15.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    "支持信息",
                    style =  MaterialTheme.typography.titleLarge
                )
                Divider(thickness=5.dp)
                Row {
                    val uriHandler = LocalUriHandler.current
                    Icon(
                        modifier = Modifier.scale(0.3f),
                        imageVector = Icons.Filled.FiberManualRecord,
                        contentDescription = "版本说明",
                    )
                    ClickableText(
                        text = buildAnnotatedString {
                            val text ="开源地址 https://github.com/xhvvvv/kcps.git"
                            append(text)
                            addStyle(SpanStyle(textDecoration = TextDecoration.Underline), 0, text.length)
                        },
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.pointerHoverIcon(PointerIcon.Hand),
                        onClick = {
                            uriHandler.openUri("https://github.com/xhvvvv/kcps.git")
                        }
                    )
                } 
                Row {
                    Icon(
                        modifier = Modifier.scale(0.3f),
                        imageVector = Icons.Filled.FiberManualRecord,
                        contentDescription = "版本说明",
                    )
                    Text(
                        "联系我 wj1939146725@gmail.com",
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 50.dp, top = 15.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    "问题说明",
                    style =  MaterialTheme.typography.titleLarge
                )
                Divider(thickness=5.dp)
                Row {
                    Icon(
                        modifier = Modifier.scale(0.3f),
                        imageVector = Icons.Filled.FiberManualRecord,
                        contentDescription = "1",
                    )
                    Text(
                        "转换格式目前仅支持TOML格式，请不要选择其他格式",
                    )
                } 
                
                Row {
                    Icon(
                        modifier = Modifier.scale(0.3f),
                        imageVector = Icons.Filled.FiberManualRecord,
                        contentDescription = "2",
                    )
                    Text(
                        "加密形式目前仅支持Token格式，请不要选择其它加密格式",
                    )
                }
                Row {
                    Icon(
                        modifier = Modifier.scale(0.3f),
                        imageVector = Icons.Filled.FiberManualRecord,
                        contentDescription = "2",
                    )
                    Text(
                        "当前强制独立运行,即每个配置都会启动一个单独的进程",
                    )
                }
                Row {
                    Icon(
                        modifier = Modifier.scale(0.3f),
                        imageVector = Icons.Filled.FiberManualRecord,
                        contentDescription = "2",
                    )
                    Text(
                        "当前支持的配置类型有：TCP、STCP、UDP、XTCP",
                    )
                }
            }
        }
    }
}
 