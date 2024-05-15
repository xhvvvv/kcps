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
import com.wxfactory.kcps.common.public.validate.ValidataeObject

@Composable
fun <T> Select(
    modifier: Modifier = Modifier,
    options: List<T>,
    primarySelect : T = options[0],
    onOptionSelected: (T)->Unit,
    enabled: Boolean = true,
    selectedOptionShow: (T)->String = { it.toString() },
    optionSelectedShow: (T)->String = { it.toString() },
    textStyle: TextStyle = MaterialTheme.typography.titleSmall,
    shape: CornerBasedShape = MaterialTheme.shapes.small,
    label: (@Composable () -> Unit)? = null,
) {
    var expanded by remember { mutableStateOf(false) }
    var chosedT by remember { mutableStateOf<T>(primarySelect) }
    Column {
        if (label != null) {
            label()
            Spacer(modifier = Modifier.height(4.dp))
        }
        Box(
            modifier = modifier
                .height(56.dp)
                .border(
                    width = 1.dp,
                    color = if (enabled) {
                        MaterialTheme.colorScheme.onBackground
                    } else {
                        MaterialTheme.colorScheme.onBackground.copy(alpha = .4f)
                    },
                    shape = shape,
                )
                .clip(shape)
                .clickable {
                    if (enabled) {
                        expanded = !expanded
                    }
                },
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
                Text(
                    text = selectedOptionShow(chosedT),
                    style = textStyle,
                )
                if (enabled) {
                    Icon(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .size(24.dp),
                        imageVector = if (expanded) {
                            Icons.Filled.ArrowDropUp
                        } else {
                            Icons.Filled.ArrowDropDown
                        },
                        contentDescription = null,
                    )
                }
            }
            DropdownMenu(
                modifier = Modifier.width(200.dp),
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                options.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = optionSelectedShow(selectionOption),
                                style = MaterialTheme.typography.labelLarge,
                            )
                        },
                        onClick = {
                            chosedT = selectionOption
                            onOptionSelected(selectionOption)
                            expanded = false
                        },
                    )
                }
            }
        }

        /*selectedOption.error?.let {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = it,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.End,
            )
        }*/
    }
}
