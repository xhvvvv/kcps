package com.wxfactory.kcps.common.screen.data

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import cn.hutool.core.io.FileUtil
import cn.hutool.core.util.SerializeUtil
import com.wxfactory.kcps.common.core.repository.FccService
import com.wxfactory.kcps.common.core.repository.FcsService
import com.wxfactory.kcps.common.core.repository.SettingService
import com.wxfactory.kcps.frpfun.entity.FrpConfigC
import com.wxfactory.kcps.frpfun.entity.FrpConfigS
import kotlinx.coroutines.flow.*
import java.io.File
import java.util.ArrayList

/**
 * 这里是处理屏幕所需要的所有的数据
 */
class ScreenViewModel(
    private val settingsRepository: SettingService,
    private val fccService: FccService,
    private val fcsService: FcsService,
) : ScreenModel {

    //app的风格
    val appTheme: StateFlow<Int?> = settingsRepository.getAppTheme().stateIn(
        scope = screenModelScope, //voyager提供的与ScreenModel绑定的声明周期协程上下位
        started = SharingStarted.WhileSubscribed(), //通知策略，表示必须有一个订阅才会开始发送
        initialValue = null,
    )
    val exeFile : String?
        get() {
           return settingsRepository.getExeLocation()
        }
    fun setExeLocation( file : String?) {
        settingsRepository.saveExeLocation(file)
    }

    val exeSFile : String?
        get() {
           return settingsRepository.getExesLocation()
        }
    fun setExeSLocation( file : String?) {
        settingsRepository.saveExesLocation(file)
    }

    val confType : StateFlow<String? > = settingsRepository.getFccTypes().stateIn(
        scope = screenModelScope, //voyager提供的与ScreenModel绑定的声明周期协程上下位
        started = SharingStarted.WhileSubscribed(), //通知策略，表示必须有一个订阅才会开始发送
        initialValue = null,
    )
    fun setConfType( type : String) {
        settingsRepository.saveFccTypes(type)
    }

    val fcs : List<FrpConfigC>? = fccService.getAllFcc() 
    fun save( fc : FrpConfigC) {
        fccService.saveOrUpdateFcc(fc)
    }
    fun save( fc : List<FrpConfigC>) {
        fccService.saveAllFcc(fc)
    }
    
    val fcServer : FrpConfigS? = fcsService.getServer()
    fun saveFcsServer( fcs : FrpConfigS ) {
        fcsService.saveFcs(fcs)
    }
}