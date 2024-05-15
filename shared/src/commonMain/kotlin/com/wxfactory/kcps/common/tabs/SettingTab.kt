package com.wxfactory.kcps.common.tabs

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.wxfactory.kcps.common.public.Select
import com.wxfactory.kcps.common.screen.data.ScreenViewModel
import com.wxfactory.kcps.frpfun.translate.ConfigTypes
import org.koin.compose.koinInject
import java.awt.FileDialog


class SettingTab() : Tab{
    override val options: TabOptions
        @Composable
        get() {
            val title = "Setting"
            val imageVector = rememberVectorPainter( Icons.Outlined.Settings)
            return TabOptions(
                index = 1u,
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
    var location:String by remember{ mutableStateOf(mainViewModel.exeFile?:"") }
    val type = mainViewModel.confType.collectAsState().value;
    
    Scaffold(
        bottomBar = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement =  Arrangement.End
            ){
                Button(onClick ={
                    mainViewModel.setExeLocation(location)
                }){
                    Text("确定保存")
                }
            }
        }
    ){ innerPadding ->
        Card(
            modifier = Modifier.padding(innerPadding),
        ) {
            txb("执行文件位置"){
                
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(0.7f),
                    value = location,
                    onValueChange = {
                        location = it
                    },
                    singleLine = true
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
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
                                location= "$directory$file"
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

            txb("配置文件格式"){
                Select<ConfigTypes>(
                    modifier = Modifier.fillMaxWidth(0.3f),
                    options = ConfigTypes.values().toList(),
                    selectedOptionShow  = { it.name },
                    optionSelectedShow  = { it.name },
                    onOptionSelected = { ii -> mainViewModel.setConfType(ii.name) }
                )
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
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(0.2f),
            text = title,
            textAlign = TextAlign.Center
        )
        control()
    }
}