package com.wxfactory.kcps.common.public

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun  CheckBox(
    modifier: Modifier = Modifier,
    shape: CornerBasedShape = MaterialTheme.shapes.small,
    currentValue: Boolean,
    onValueChange: (Boolean) -> Unit,
    editable : Boolean = true,
    label: (@Composable () -> Unit)? = null
) {
    var chosed by remember { mutableStateOf(currentValue) }
    Column {
        if (label != null) {
            label()
            Spacer(modifier = Modifier.height(4.dp))
        }
        Box(
            modifier = modifier
                .height(56.dp)
                .defaultMinSize(minWidth = 40.dp)
                .clip(shape),
            contentAlignment = Alignment.CenterStart,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 8.dp,
                        horizontal = 12.dp,
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Checkbox(
                    checked = chosed,
                    onCheckedChange = {
                        chosed = it
                        onValueChange(it)
                    },
                    enabled = editable 
                )
            }
            
        }
    }
}
