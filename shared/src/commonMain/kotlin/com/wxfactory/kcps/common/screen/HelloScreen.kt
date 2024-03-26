package com.wxfactory.kcps.common.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.HourglassEmpty
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.wxfactory.kcps.common.public.ExtendCard
import com.wxfactory.kcps.common.public.InputNo1
import com.wxfactory.kcps.common.screen.data.ScreenViewModel
import com.wxfactory.kcps.common.util.i18N
import com.wxfactory.kcps.frpfun.entity.FrpConfig
import com.wxfactory.kcps.frpfun.entity.FrpConfigC
import org.koin.compose.koinInject

class HelloScreen : Screen {
    @Composable
    override fun Content() {
        Box{
            Row { 
                
            }
        }
    }
}
 

@Composable
fun SettingsScreen(
    mainViewModel: ScreenViewModel = koinInject<ScreenViewModel>(),
) {
    val darkTheme = when (mainViewModel.appTheme.collectAsState().value) {
        1 -> true
        else -> false
    }
    SettingsScreenContent(  )
 
}

@Composable
fun SettingsScreenContent(
    frpConfigs : List<FrpConfig>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
       this.items( items = frpConfigs , key = { x -> x.name } ){
           if (it is FrpConfigC){
               
           }else{
               TODO("服务端")
           }
       }
 
}

@Composable
fun FrpCPanel(
    focusSessionMinutes: Int,
    onFocusSessionMinutesChange: (String) -> Unit,
    shortBreakMinutes: Int,
    onShortBreakMinutesChange: (String) -> Unit,
    longBreakMinutes: Int,
    onLongBreakMinutesChange: (String) -> Unit,
    onExpand: (String) -> Unit,
    expanded: (String) -> Boolean,
    fc : FrpConfigC,
) {
    ExtendCard(
        onExpand = {
            onExpand("Focus Sessions")
        },
        expanded = expanded("Focus Sessions"),
        name = fc.name,
        icon = Icons.Outlined.HourglassEmpty,
        content = {
            var autoStartBreaks by remember { mutableStateOf(false) }
            var autoStartFocusSession by remember { mutableStateOf(false) }
            
            //3个输入框一行
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                //名称修改
                InputNo1(
                    modifier = Modifier.weight(1f),
                    title = i18N.getProperty("cg-name"),
                    currentValue = focusSessionMinutes.toString(),
                    onValueChange = {
                        onFocusSessionMinutesChange(it)
                    },
                )
                
                //名称修改
                InputNo1(
                    modifier = Modifier.weight(1f),
                    title = i18N.getProperty("cg-name"),
                    currentValue = focusSessionMinutes.toString(),
                    onValueChange = {
                        onFocusSessionMinutesChange(it)
                    },
                )
                
                
            }
            Spacer(modifier = Modifier.height(12.dp))
            AutoStartSession(
                title = "Auto Start Breaks",
                checked = autoStartBreaks,
                onCheckedChange = {
                    autoStartBreaks = it
                },
            )
            Spacer(modifier = Modifier.height(12.dp))
            AutoStartSession(
                title = "Auto Start Sessions",
                checked = autoStartFocusSession,
                onCheckedChange = {
                    autoStartFocusSession = it
                },
            )
        },
    )
}
 