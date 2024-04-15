package com.wxfactory.kcps.common.public

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.wxfactory.kcps.common.core.entity.FrpConfigCCompose
import com.wxfactory.kcps.common.platform.frpfun.startFrp
import com.wxfactory.kcps.frpfun.entity.FrpConfigC

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun fccExtendCard(
    fc: FrpConfigCCompose<FrpConfigC>,
    modifier: Modifier = Modifier,
    onExpand: () -> Unit, //切换展开时的调用
    content: @Composable ( fc: FrpConfigCCompose<FrpConfigC> ) -> Unit,
) {
    var ifExpend : Boolean  by  mutableStateOf(fc.expend)
    var opend : Boolean by mutableStateOf(fc.ifrunnig ) 
    var ifRunOnly : Boolean by mutableStateOf(fc.ifrunnig ) 
    Card(
        modifier = modifier,
        onClick = {
            onExpand()
            ifExpend = ifExpend.not()
            fc.expend = ifExpend
        },
    ) {
        Column(
            modifier = modifier.padding(16.dp),
        ) {
            // 展示信息
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    //前
                    Column(modifier = Modifier.weight(0.2f)) {
                        Row {
                            Icon(
                                imageVector = Icons.Outlined.HourglassEmpty,
                                contentDescription = "配置名称",
                            )
                            Text(
                                text = fc.fc.name,
                                style = MaterialTheme.typography.titleSmall,
                            )
                        }
                    }
                    //中
                    Column(modifier = Modifier.weight(0.4f)) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                        ) {
                            Card(
                                modifier=Modifier.fillMaxWidth(0.2f)
                            ) {
                                Icon(
                                    modifier = Modifier.align(Alignment.CenterHorizontally),
                                    imageVector = Icons.Outlined.Category,
                                    contentDescription = "协议类型",
                                )
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = fc.fc.type,
                                    style = MaterialTheme.typography.titleSmall,
                                    textAlign = TextAlign.Center
                                )
                            }
                            Spacer(Modifier.width(2.dp))
                            
                            Divider(modifier= Modifier.width(5.dp).heightIn(min = 40.dp))
                            Card(
                                modifier=Modifier.fillMaxWidth(0.2f)
                            ) {
                                Icon(
                                    modifier = Modifier.align(Alignment.CenterHorizontally),
                                    imageVector = Icons.Outlined.Circle,
                                    contentDescription = "占用端口",
                                )
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = fc.fc.localPort?.toString()?:"",
                                    style = MaterialTheme.typography.titleSmall,
                                    textAlign = TextAlign.Center
                                )
                            }
                            Spacer(Modifier.width(2.dp))
                            Divider(modifier= Modifier.width(5.dp).heightIn(min = 40.dp))
                            Card(
                                modifier=Modifier.fillMaxWidth(0.5f)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        modifier = Modifier.padding(3.dp) ,
                                        imageVector = Icons.Outlined.Dns,
                                        contentDescription = "中间服务商",
                                    )
                                    Column  {
                                        Text(
//                                            modifier = Modifier.fillMaxWidth(),
                                            text = "${fc.fc.host}",
                                            style = MaterialTheme.typography.titleSmall,
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
//                                            modifier = Modifier.fillMaxWidth(),
                                            text = "${fc.fc.port}",
                                            style = MaterialTheme.typography.titleSmall,
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                }
                                
                              
                               
                            }
                        }
                           
                    }
                    
//                    后
                    Column(modifier = Modifier , /*.weight(0.5f),*/
                        horizontalAlignment = Alignment.End
                    ) {
                         Row(
                            /* modifier = Modifier.background(color = Color.Red),*/
                             verticalAlignment = Alignment.CenterVertically
                         ) {
                             Column(
                                 verticalArrangement = Arrangement.Center,
                                 horizontalAlignment = Alignment.CenterHorizontally
                             ){
                                 Checkbox(
                                     modifier = Modifier.padding(0.dp),
                                     checked = ifRunOnly,
                                     onCheckedChange = {
                                         fc.ifrunnig = it
                                         ifRunOnly = it 
                                     }
                                 )
                                 Text(
                                     "独立运行",
                                     style = MaterialTheme.typography.titleSmall,
                                     textAlign = TextAlign.Center
                                 
                                 )
                             }
                             Spacer(Modifier.width(16.dp))
                             IconButton(
                                 colors = IconButtonDefaults.iconButtonColors(
                                     containerColor  = Color(0xFFFFD8E4),
                                 ),
                                 onClick = {
                
                                 }
                             ) {
                                 Icon(
                                     imageVector = Icons.Outlined.Delete,
                                     contentDescription = "删除",
                                 )
                             }
                             Spacer(Modifier.width(16.dp))
                             Switch(
                                 checked = opend,
                                 onCheckedChange = {
                                     fc.ifrunnig = it
                                    
//                                     LaunchedEffect(it){
//                                         startFrp(fc)
//                                     }
                                     
                                     opend = it
                                 }
                             )
                             Spacer(Modifier.width(16.dp))
                             // 下拉按钮
                             IconButton(onClick = {
                                 onExpand()
                                 ifExpend = ifExpend.not()
                                 fc.expend = ifExpend
                             }) {
                                 Icon(
                                     imageVector = if (ifExpend) {
                                         Icons.Rounded.KeyboardArrowUp
                                     } else {
                                         Icons.Rounded.KeyboardArrowDown
                                     },
                                     contentDescription = null,
                                 )
                             }
                         }
                    }
                }

                
                
            AnimatedVisibility(ifExpend) {
                Column {
                    Spacer(modifier = Modifier.height(8.dp))
                    content(fc)
                }
            }
        }
    }
}