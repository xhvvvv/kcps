package com.wxfactory.kcps.common.platform.frpfun

import com.wxfactory.kcps.common.core.entity.FrpConfigCCompose
import com.wxfactory.kcps.common.screen.data.ScreenViewModel
import com.wxfactory.kcps.frpfun.Fc2Start
import com.wxfactory.kcps.frpfun.entity.FrpConfig
import com.wxfactory.kcps.frpfun.entity.FrpConfigC
import com.wxfactory.kcps.frpfun.entity.FrpConfigS
import com.wxfactory.kcps.frpfun.frpBash.bash.ExpandExecuteResultHandler
import com.wxfactory.kcps.frpfun.frpBash.entity.ExcuteCon
import com.wxfactory.kcps.frpfun.translate.ConfigTypes
import org.apache.commons.exec.ExecuteException
import org.koin.java.KoinJavaComponent.get


/**
 * 启动配置
 */
actual fun startFrp( frp: FrpConfigCCompose<FrpConfig> ): ExcuteCon {
    val mainViewModel: ScreenViewModel = get(ScreenViewModel::class.java)
    frp.exeStartCallBack()
    var con:ExcuteCon? = null  
    if (frp.fc is FrpConfigC) {
        con = 
            Fc2Start.start(
                frp.fc  ,
                mutableMapOf<String , Any>(
                    Pair(Fc2Start.EXE_LOCATION, mainViewModel.exeFile as Any),
                    Pair(Fc2Start.EXE_CONFIG_TYPE, ConfigTypes.valueOf(mainViewModel.confType.value?:ConfigTypes.TOML.name) as Any),
                    Pair(Fc2Start.EXE_CALLBACK, ExpandExecuteResultHandler{ exitV: Int?, ex: ExecuteException? ->
                        ex?.let {
    
                        }
                        exitV?.let {
    
                        }
                        /*总之就是退出了*/
                        frp.exeCallBack()
                        frp.ec = null
                        frp.ifrunnig.value = false
                    })
                )
            )
    }else if (frp.fc is FrpConfigS){
        con =
            Fc2Start.startS(
                frp.fc  ,
                mutableMapOf<String , Any>(
                    Pair(Fc2Start.EXE_LOCATION, mainViewModel.exeSFile as Any),
                    Pair(Fc2Start.EXE_CONFIG_TYPE, ConfigTypes.valueOf(mainViewModel.confType.value?:ConfigTypes.TOML.name) as Any),
                    Pair(Fc2Start.EXE_CALLBACK, ExpandExecuteResultHandler{ exitV: Int?, ex: ExecuteException? ->
                        ex?.let {

                        }
                        exitV?.let {

                        }
                        /*总之就是退出了*/
                        frp.exeCallBack()
                        frp.ec = null
                        frp.ifrunnig.value = false
                    })
                )
            )
    }
    frp.ec = con
    return con!!;
}
 
actual fun stopFrp( frp: FrpConfigCCompose<FrpConfig> ) {
    frp.ec?.let {
        //关闭
        it?.executeWatchdog?.destroyProcess()
    }
    frp.exeCallBack()
    frp.ec = null
}