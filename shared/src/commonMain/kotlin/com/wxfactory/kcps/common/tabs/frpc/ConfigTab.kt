package com.wxfactory.kcps.common.tabs.frpc

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.screenModelScope
import cafe.adriel.voyager.navigator.bottomSheet.BottomSheetNavigator
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.wxfactory.kcps.common.core.entity.FrpConfigCCompose
import com.wxfactory.kcps.common.core.moudle.FC_C
import com.wxfactory.kcps.common.platform.frpfun.stopFrp
import com.wxfactory.kcps.common.public.*
import com.wxfactory.kcps.common.public.validate.intValidator
import com.wxfactory.kcps.common.public.validate.nnullValidator
import com.wxfactory.kcps.common.screen.data.ScreenViewModel
import com.wxfactory.kcps.common.util.i18N
import com.wxfactory.kcps.frpfun.entity.FrpConfig
import com.wxfactory.kcps.frpfun.entity.FrpConfigC
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import org.koin.core.qualifier.named


class ConfigTab() : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = i18N.getProperty("tab-configTab")
            val imageVector = rememberVectorPainter(Icons.Outlined.FlagCircle)
            return TabOptions(
                index = 0u,
                title = i18N.getProperty("tab-configTab"),
                icon = imageVector,
            )
        }

    @Composable
    override fun Content() {
        SettingsScreen()
    }

}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SettingsScreen(
    mainViewModel: ScreenViewModel = koinInject<ScreenViewModel>(),
    fcs: MutableList<FrpConfigCCompose<FrpConfigC>> = koinInject<MutableList<FrpConfigCCompose<FrpConfigC>>>(named(FC_C)),
) {
    val stateFcs = remember(fcs) { 
        fcs.toMutableStateList()
    }
    BottomSheetNavigator(
        sheetShape = MaterialTheme.shapes.medium,
    ) {
        val snackbarHostState = remember { SnackbarHostState() }
        Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            },
            bottomBar = {
                BottomAppBar(
                    actions = {
                        Row(
                        ) {
                            IconButton(
                                colors = IconButtonDefaults.iconButtonColors(
                                    containerColor = MaterialTheme.colorScheme.primary,
                                ),
                                onClick = {
                                    mainViewModel.screenModelScope.launch {
                                        stateFcs.forEach {
                                            if(it.ifrunnig.value){
                                                stopFrp(it as FrpConfigCCompose<FrpConfig>)
                                            }
                                        }
                                    }
                                }
                            ) {
                                Icon(
                                    painter = rememberVectorPainter(image = Icons.Outlined.Cancel),
                                    contentDescription = "关闭全部",
                                    tint = MaterialTheme.colorScheme.onPrimary
                                )
                            }

                            Spacer(modifier = Modifier.width(10.dp))
                            IconButton(
                                colors = IconButtonDefaults.iconButtonColors(
                                    containerColor = MaterialTheme.colorScheme.primary,
                                ),
                                onClick = {
                                    stateFcs.forEach {
                                        it.expend.value = true
                                    }
                                }
                            ) {
                                Icon(
                                    painter = rememberVectorPainter(image = Icons.Outlined.UnfoldMore),
                                    contentDescription = "展开",
                                    tint = MaterialTheme.colorScheme.onPrimary
                                )
                            }

                            Spacer(modifier = Modifier.width(10.dp))
                            IconButton(
                                colors = IconButtonDefaults.iconButtonColors(
                                    containerColor = MaterialTheme.colorScheme.primary,
                                ),
                                onClick = {
                                    stateFcs.forEach {
                                        it.expend.value = false
                                    }
                                }
                            ) {
                                Icon(
                                    painter = rememberVectorPainter(image = Icons.Outlined.UnfoldLess),
                                    contentDescription = "收起",
                                    tint =  MaterialTheme.colorScheme.onPrimary
                                )
                            }

                        }
                    },
                    floatingActionButton = {
                        val bottomSheetNavigator = LocalBottomSheetNavigator.current
                        FloatingActionButton(
                            onClick = {
                                bottomSheetNavigator.show(
                                    AddPanel(stateFcs) { tfc ->
                                        it.hide()
                                        fcs.add(FrpConfigCCompose(tfc))
                                        stateFcs.add(FrpConfigCCompose(tfc))
                                    }
                                )
                            },
                            containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                            elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                        ) {
                            Icon(Icons.Filled.Add, "添加配置")
                        }
                    }
                )
            },
        ) { innerPadding ->
            if (stateFcs.isNullOrEmpty()) {
                guideYou( modifier = Modifier.padding(innerPadding))
            } else {
                SettingsScreenContent(
                    modifier = Modifier.padding(innerPadding),
                    stateFcs,
                    alert = {
                        mainViewModel.screenModelScope.launch {
                            mainViewModel.screenModelScope.launch {
                                snackbarHostState.showSnackbar(it)
                            }
                            mainViewModel.screenModelScope.launch {
                                delay(1000)
                                snackbarHostState.currentSnackbarData?.dismiss()
                            }
                        }
                    }
                ) {
                    fcs.remove(it)
                    stateFcs.remove(it)
                }
            }

        }
    }

}

@Composable
fun SettingsScreenContent(
    modifier: Modifier = Modifier.padding(10.dp),
    frpConfigs: MutableList<FrpConfigCCompose<FrpConfigC>>,
    alert: (String) -> Unit, //提醒框
    removeFc: (FrpConfigCCompose<FrpConfigC>) -> Unit,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {

        this.itemsIndexed(
            items = frpConfigs,
            key = { index , x -> (x.fc.id) }
        ) { 
          index, fc ->
            Form {
                val formState = LocalFormState.current
                LaunchedEffect(Unit) {
                    //注册校验器
                    formState {
                        "name" useValidator nnullValidator
                        "host" useValidator nnullValidator
                        "port" useValidators listOf(
                            nnullValidator, intValidator
                        )

//                "localIP" useValidators listOf(
//                    nnullValidator,intValidator
//                ) 
                        "localPort" useValidators listOf(
                            nnullValidator, intValidator
                        )

                        "remotePort" useValidators listOf(
                            nnullValidator, intValidator
                        )

                        "serverName" useValidator nnullValidator
                        "secretKey" useValidator nnullValidator
                    }
                }
                fccExtendCard(
                    index+1,
                    fc,
                    onExpand = { },
                    removeFc = removeFc,
                    alert = alert
                ) {  
                    // 下展开的栏目
                    topShow(fc = it);
                }
            }
        }
    }
}

/**
 * 初始化开始时，指引你操作
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun guideYou(
    modifier: Modifier = Modifier.padding(10.dp),
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .fillMaxHeight()
                .padding(10.dp)
            ,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f)
                    .padding(bottom = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.inversePrimary
                )
            ) {
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = "欢迎使用wxfactory-kcps",
                    style = MaterialTheme.typography.titleMedium
                )
            }


            FlowRow(
                maxItemsInEachRow = 2,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(0.45f),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.inversePrimary
                    )
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            modifier = Modifier
                                .graphicsLayer(
                                    rotationX = 180f, // 水平翻转
                                    rotationY = 180f, // 水平翻转
                                )
                                .size(width = 60.dp, height = 120.dp),
                            imageVector = Icons.Filled.SwitchAccessShortcut,
                            contentDescription = "指向左"
                        )
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                modifier = Modifier.padding(4.dp),
                                text = "左下功能",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                modifier = Modifier.padding(2.dp),
                                text = "关闭全部",
                                style = MaterialTheme.typography.bodyMedium
                            )

                            Text(
                                modifier = Modifier.padding(2.dp),
                                text = "展开全部",
                                style = MaterialTheme.typography.bodyMedium
                            )

                            Text(
                                modifier = Modifier.padding(2.dp),
                                text = "收起全部",
                                style = MaterialTheme.typography.bodyMedium
                            )


                        }


                    }
                }

                Card(
                    modifier = Modifier.fillMaxWidth(0.45f),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.inversePrimary
                    )
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier.padding(4.dp).fillMaxWidth(0.63f),
                            text = "点击添加按钮，开始穿透吧！",
                            style = MaterialTheme.typography.titleMedium
                        )

                        Icon(
                            modifier = Modifier
                                .graphicsLayer(
                                    rotationX = 180f, // 水平翻转
                                )
                                .size(width = 60.dp, height = 120.dp)
                                .fillMaxWidth(0.37f),
                            imageVector = Icons.Filled.SwitchAccessShortcut,
                            contentDescription = "指向左"
                        )
                    }
                }
            }

        }
         
    }
}
