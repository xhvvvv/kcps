package com.wxfactory.kcps.common.core.entity

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import com.wxfactory.kcps.frpfun.entity.FrpConfig
import com.wxfactory.kcps.frpfun.frpBash.entity.ExcuteCon
import kotlinx.serialization.Serializable

@Stable
open class FrpConfigCCompose<T : FrpConfig>(public val fc : T ){
    var expend : MutableState<Boolean> = mutableStateOf(false)
    /**是否正在运行*/
    var ifrunnig : MutableState<Boolean> = mutableStateOf(false)
    /**独立运行*/
    var ifrunOnly : Boolean = true
    
    var ec : ExcuteCon? = null
    lateinit var exeStartCallBack : () -> Unit
    /**运行结束的回调方法*/
    lateinit var exeCallBack : () -> Unit
}
