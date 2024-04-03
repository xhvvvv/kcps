package com.wxfactory.kcps.common.tabs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Engineering
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.HourglassEmpty
import androidx.compose.material3.Card
import androidx.compose.material3.Text
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


class AboutTab() : Tab{
    override val options: TabOptions
        @Composable
        get() {
            val title = "Author"
            val imageVector = rememberVectorPainter( Icons.Outlined.Engineering)
            return TabOptions(
                index = 1u,
                title = title,
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
    mainViewModel: ScreenViewModel = koinInject<ScreenViewModel>(),
) {
    Card() {
        Text(text = "关于作者")
    }
}
 