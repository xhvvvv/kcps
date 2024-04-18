package com.wxfactory.kcps.common.core.entity

import com.wxfactory.kcps.frpfun.entity.FrpConfig
import com.wxfactory.kcps.frpfun.frpBash.entity.ExcuteCon

open class FrpConfigCCompose<T : FrpConfig>(public val fc : T ){
    var expend : Boolean = false
    /**是否正在运行*/
    var ifrunnig : Boolean = false
    /**独立运行*/
    var ifrunOnly : Boolean = false
    
    var ec : ExcuteCon? = null

    /**运行结束的回调方法*/
    lateinit var exeCallBack : () -> Unit
}