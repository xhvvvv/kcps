package com.wxfactory.kcps.common.public

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExtendCard(
    name: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
    onExpand: () -> Unit, //切换展开时的调用
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
                                imageVector = icon,
                                contentDescription = name,
                            )
                            Text(
                                text = name,
                                style = MaterialTheme.typography.titleSmall,
                            )
                        }
                    }
                    
                    //中
                    Column(modifier = Modifier.weight(0.4f)) {
                        Row {
                            Text(
                                text = "类型",
                                style = MaterialTheme.typography.titleSmall,
                            )
                            Text(
                                text = "简介",
                                style = MaterialTheme.typography.titleSmall,
                            )
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
                    content()
                }
            }
        }
    }
}