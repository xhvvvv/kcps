package com.wxfactory.kcps.common.tabs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.screenModelScope
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.wxfactory.kcps.common.core.entity.FrpConfigCCompose
import com.wxfactory.kcps.common.public.InputNo1
import com.wxfactory.kcps.common.screen.data.ScreenViewModel
import com.wxfactory.kcps.common.util.i18N
import com.wxfactory.kcps.frpfun.entity.FrpConfig
import com.wxfactory.kcps.frpfun.entity.FrpConfigC
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import org.koin.core.qualifier.named
import java.awt.FileDialog
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible


class SettingTab() : Tab{
    override val options: TabOptions
        @Composable
        get() {
            val title = "Setting"
            val imageVector = rememberVectorPainter( Icons.Outlined.Settings)
            return TabOptions(
                index = 3u,
                title = title,
                icon = imageVector,
            )
        }
    @Composable
    override fun Content() {
        settingPanel()
    }

}
 

@Composable
private fun settingPanel(
    mainViewModel: ScreenViewModel = koinInject<ScreenViewModel>(),
) {
    val scope = rememberCoroutineScope()
    val location =  mainViewModel.exeFile.collectAsState();
    Scaffold{ innerPadding ->
        Card(
            modifier = Modifier.padding(innerPadding)
        ) {
            txb("执行文件位置"){
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(0.7f),
                        value = location.value,
                        onValueChange = {
                            mainViewModel.screenModelScope.launch {
                                mainViewModel.exeFile.emit(it)
                            }
                        },
                    )
                    IconButton(
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor  = Color(0xFFFFD8E4),
                        ),
                        onClick = {
                                val fileDialog = FileDialog(ComposeWindow())
                                fileDialog.isVisible = true
                                val directory = fileDialog.directory
                                val file = fileDialog.file
                                if (directory != null && file != null) {
                                    mainViewModel.screenModelScope.launch {
                                        mainViewModel.exeFile.emit("$directory$file")
                                    }
                                   
                                }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Folder,
                            contentDescription = "选择文件",
                        )
                    } 
                
            }
        }
    }
}
@Composable
fun txb(
    title : String,
    control : @Composable () -> Unit
){
    Row(
        modifier = Modifier.fillMaxWidth().padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(0.2f),
            text = title,
            textAlign = TextAlign.Center
        )
        control()
    }
}