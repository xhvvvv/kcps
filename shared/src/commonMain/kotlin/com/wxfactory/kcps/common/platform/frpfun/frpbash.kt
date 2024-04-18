package com.wxfactory.kcps.common.platform.frpfun

import com.wxfactory.kcps.common.core.entity.FrpConfigCCompose
import com.wxfactory.kcps.frpfun.entity.FrpConfig
import com.wxfactory.kcps.frpfun.entity.FrpConfigC
import com.wxfactory.kcps.frpfun.frpBash.entity.ExcuteCon


/**
 * 启动配置
 */
expect fun startFrpC(frp: FrpConfigCCompose<FrpConfigC>) : ExcuteCon