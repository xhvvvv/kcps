package com.wxfactory.kcps.common.public

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
    onExpand: () -> Unit, //下拉展示的面板
    expanded: Boolean,
) {
    Card(
        modifier = modifier,
        onClick = {
            onExpand()
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
                    Icon(
                        imageVector = icon,
                        contentDescription = name,
                    )
                    Text(
                        text = name,
                        style = MaterialTheme.typography.titleLarge,
                    )
                }

                
                // 下拉按钮
                IconButton(onClick = { onExpand() }) {
                    Icon(
                        imageVector = if (expanded) {
                            Icons.Rounded.KeyboardArrowUp
                        } else {
                            Icons.Rounded.KeyboardArrowDown
                        },
                        contentDescription = null,
                    )
                }
            }
            AnimatedVisibility(expanded) {
                Column {
                    Spacer(modifier = Modifier.height(8.dp))
                    content()
                }
            }
        }
    }
}