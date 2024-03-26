package com.wxfactory.kcps.common.public

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/**
 * 配置Frp页使用的输入框
 * 
 */
@Composable
fun InputNo1(
    modifier: Modifier = Modifier,
    title: String,
    currentValue: String,
    onValueChange: (String) -> Unit,
) {
    BloomInputTextField(
        modifier = modifier,
        textStyle = MaterialTheme.typography.bodyMedium.copy(
            textAlign = TextAlign.Start,
        ),
        label = {
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                ),
            )
        },
        value = TextFieldState(currentValue),
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number,
        ),
    )
}


@Composable
internal fun BloomInputTextField(
    modifier: Modifier = Modifier,
    label: (@Composable () -> Unit)? = null,
    placeholder: (@Composable () -> Unit)? = null,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    value: TextFieldState,
    maxLines: Int = 1,
    editable: Boolean = true,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle = MaterialTheme.typography.titleSmall,
    shape: CornerBasedShape = MaterialTheme.shapes.small,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
) {
    Column(
        modifier = modifier,
    ) {
        if (label != null) {
            label()
            Spacer(modifier = Modifier.height(4.dp))
        }
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth()
                .defaultMinSize(minWidth = 56.dp),
            value = value.text,
            onValueChange = onValueChange,
            placeholder = placeholder,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            textStyle = textStyle,
            shape = shape,
            maxLines = maxLines,
            singleLine = maxLines == 1,
            keyboardOptions = keyboardOptions,
            readOnly = !editable,
        )
        
        //校验提示信息
        if (!value.error.isNullOrEmpty()) {
            Text(
                text = value.error,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.error,
                ),
            )
        }
    }
}


data class TextFieldState(
    val text: String = "",
    val error: String? = null,
)