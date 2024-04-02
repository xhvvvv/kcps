package com.wxfactory.kcps.common.tabs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.HourglassEmpty
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.wxfactory.kcps.common.public.ExtendCard
import com.wxfactory.kcps.common.public.InputNo1
import com.wxfactory.kcps.common.screen.data.ScreenViewModel
import com.wxfactory.kcps.common.util.i18N
import com.wxfactory.kcps.frpfun.entity.FrpConfig
import com.wxfactory.kcps.frpfun.entity.FrpConfigC
import org.koin.compose.koinInject
import org.koin.core.qualifier.named
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible


class ConfigTab() : Tab{
    override val options: TabOptions
        @Composable
        get() {
            val title = "Home"
            val imageVector = rememberVectorPainter( Icons.Outlined.Home)
            return TabOptions(
                index = 0u,
                title = title,
                icon = imageVector,
            )
        }
    @Composable
    override fun Content() {
        SettingsScreen()
    }

}
 

@Composable
fun SettingsScreen(
    mainViewModel: ScreenViewModel = koinInject<ScreenViewModel>(),
    fcs: MutableList<FrpConfig> = koinInject<MutableList<FrpConfig>>(named("fcs")),
) {
    val darkTheme = when (mainViewModel.appTheme.collectAsState().value) {
        1 -> true
        else -> false
    }
    SettingsScreenContent(fcs)
}

@Composable
fun SettingsScreenContent(
    frpConfigs : MutableList<FrpConfig>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)

        ,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        this.items(
            items = frpConfigs,
            key = { x ->
                x.name.hashCode()
            }
        ) {
            if (it is FrpConfigC) {
                FrpCPanel(
                    onExpand = { it -> println("切换") },
                    it
                )
            } else {
                TODO("服务端")
            }
        }

    }

}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FrpCPanel(
    onExpand: (String) -> Unit,
    fc: FrpConfigC,
) {
    ExtendCard(
        onExpand = {
            onExpand("Focus Sessions")
        },
        name = fc.name,
        icon = Icons.Outlined.HourglassEmpty,
        content = {
            val allpropertis  =  fc::class.memberProperties
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                maxItemsInEachRow = 5
            ){
                for (ap in allpropertis) {
                    ap.isAccessible = true
                    i18N.getProperty("cg-${ap.name}")?.let{
                        InputNo1(
                            modifier = Modifier.width(200.dp),
                            title = it,
                            currentValue = ap.call(fc).run {
                                if (this == null) return@run "" else return@run this as String
                            },
                            onValueChange = {
                                if (ap is KMutableProperty<*>) {
                                    //修改属性值，调用setter方法
                                    ap.setter.call(fc, it)
                                }
                            },
                        )
                    }
                }
            }
        },
    )
}