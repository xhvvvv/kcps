package com.wxfactory.kcps.common.core.entity

import com.wxfactory.kcps.frpfun.entity.FrpConfig

open class FrpConfigCCompose<T : FrpConfig>(public val fc : T ){
    var expend : Boolean = false
    /**是否正在运行*/
    var ifrunnig : Boolean = false
    /**独立运行*/
    var ifrunOnly : Boolean = false
}