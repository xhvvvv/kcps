package com.wxfactory.kcps.common.public

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wxfactory.kcps.common.public.validate.ValidataeObject
import com.wxfactory.kcps.common.public.validate.Validator

/**
 * 配置Frp页使用的输入框
 * 
 */
@Composable
fun InputNo1(
    id : String? = null , //校验的唯一标识
    modifier: Modifier = Modifier,
    title: String,
    labelTextStyle: TextStyle = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
    type : KeyboardType = KeyboardType.Text,
    currentValue: String,
    onValueChange: (String?) -> Unit,
    editable : Boolean = true,
    placeholder: String = ""
) {
    val fontType = MaterialTheme.typography.bodySmall.copy(color = Color.LightGray)
    val formState = LocalFormState.current
    val date = remember {  ValidataeObject(currentValue)  }
    
    LaunchedEffect(Unit){
        //注册校验者
        id?.let{
            formState.regiest(id,date)
        }
    }
    DisposableEffect(Unit){
        //去除校验者
        onDispose {
            formState.unRegiest(id)
        }
    }

    MyInputTextField(
        modifier = modifier,
        textStyle = MaterialTheme.typography.bodySmall,
        label = {
            Text(
                text = title,
                style = labelTextStyle,
            )
        },
        value = date,
        onValueChange = {
            val con =if (id!=null) formState.virfySingle(id) else true
            if (con){
                onValueChange(it)
            }else{
                onValueChange(null)
            }
        },
        keyboardOptions = KeyboardOptions( keyboardType = type),
        editable =editable,
        placeholder = { 
            Text(
                text = placeholder ,
                style = fontType
            )
        }
    )
}


@Composable
internal fun MyInputTextField(
    modifier: Modifier = Modifier,
    label: (@Composable () -> Unit)? = null,
    placeholder: (@Composable () -> Unit)? = null,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    value: ValidataeObject,
    maxLines: Int = 1,
    editable: Boolean = true,
    onValueChange: (String?) -> Unit,
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
                .defaultMinSize(minWidth = 40.dp)
                .scale(scaleY = 0.8f, scaleX = 1f)
            ,
            value = value.value?:"",
            onValueChange = {
                value.value = it
                onValueChange(it)
            },
            placeholder = placeholder,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            textStyle = textStyle,
            shape = shape,
            maxLines = maxLines,
            singleLine = maxLines == 1,
            keyboardOptions = keyboardOptions,
            readOnly = !editable,
            isError = value.error !=null
        )
        //校验提示信息
        value.error?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.labelSmall.copy(color = Color.Red),
            )
        }
    }
}

