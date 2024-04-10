package com.wxfactory.kcps.common.public

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material.icons.outlined.HourglassEmpty
import androidx.compose.material.icons.outlined.LocalTaxi
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.wxfactory.kcps.frpfun.entity.FrpConfigC

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun fccExtendCard(
    fc: FrpConfigC,
    modifier: Modifier = Modifier,
    onExpand: () -> Unit, //切换展开时的调用
    content: @Composable ( fc: FrpConfigC ) -> Unit,
) {
    var ifExpend : Boolean by remember { mutableStateOf(false) }
    Card(
        modifier = modifier,
        onClick = {
            onExpand()
            ifExpend = ifExpend.not()
        },
    ) {
        Column(
            modifier = modifier.padding(16.dp),
        ) {
            // 展示信息
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(
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
                                text = fc.name,
                                style = MaterialTheme.typography.titleSmall,
                            )
                        }
                    }
                    //中
                    Column(modifier = Modifier.weight(0.4f)) {
                        Row {
                            Card(
                                modifier=Modifier.fillMaxWidth(0.2f)
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Category,
                                    contentDescription = "协议类型",
                                )
                                Text(
                                    text = fc.type,
                                    style = MaterialTheme.typography.titleSmall,
                                )
                            }
                            
                            Card(
                                modifier=Modifier.fillMaxWidth(0.2f)
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Circle,
                                    contentDescription = "占用端口",
                                )
                                Text(
                                    text = "",
                                    style = MaterialTheme.typography.titleSmall,
                                )
                            }
                        }
                    }
                    
//                    后
                    Column(modifier = Modifier
                        .weight(0.5f),
                        horizontalAlignment = Alignment.End
                    ) {
                         Row(
                             verticalAlignment = Alignment.CenterVertically
                         ) {
                             Switch(
                                 checked = false,
                                 onCheckedChange = {
                                     
                                 }
                             )
                             // 下拉按钮
                             IconButton(onClick = {
                                 onExpand()
                                 ifExpend = ifExpend.not()
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