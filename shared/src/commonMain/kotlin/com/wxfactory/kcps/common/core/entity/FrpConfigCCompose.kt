package com.wxfactory.kcps.common.core.entity

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import com.wxfactory.kcps.frpfun.entity.FrpConfig
import com.wxfactory.kcps.frpfun.frpBash.entity.ExcuteCon
import kotlinx.coroutines.sync.Mutex
import kotlinx.serialization.Serializable

@Stable
open class FrpConfigCCompose<T : FrpConfig>(public val fc : T ){
    
    //协程锁,锁住本对象
    val mutex = Mutex()
    
    var expend : MutableState<Boolean> = mutableStateOf(false)
    /**是否正在运行*/
    var ifrunnig : MutableState<Boolean> = mutableStateOf(false)
    /**是否正在操作,指的是正在启动或者正在停止的过程中,操作期间不允许其它操作*/
    var ifLoading : MutableState<Boolean> = mutableStateOf(false)
    /**独立运行*/
    var ifrunOnly : Boolean = true
    
    var ec : ExcuteCon? = null
    /**运行成功后 才回调的方法*/
    lateinit var exeStartCallBack : () -> Unit
    /**运行结束的回调方法*/
    lateinit var exeCallBack : () -> Unit
    
    /**自软件启动后，是否已经启动过这个配置*/
    var ifStartOnce : Boolean = false
    /**忙碌中.....*/
    fun getIfBusy():Boolean{
        return ifrunnig.value || ifLoading.value
    }
}
