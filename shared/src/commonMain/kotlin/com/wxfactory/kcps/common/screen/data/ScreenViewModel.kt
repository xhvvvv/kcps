package com.wxfactory.kcps.common.screen.data

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.wxfactory.kcps.common.core.repository.SettingService
import kotlinx.coroutines.flow.*

class ScreenViewModel(
    settingsRepository: SettingService,
) : ScreenModel {

    //app的风格
    val appTheme: StateFlow<Int?> = settingsRepository.getAppTheme().stateIn(
        scope = screenModelScope, //voyager提供的与ScreenModel绑定的声明周期协程上下位
        started = SharingStarted.WhileSubscribed(), //通知策略，表示必须有一个订阅才会开始发送
        initialValue = null,
    )
    val exeFile : MutableStateFlow<String>  = MutableStateFlow<String>( settingsRepository.getExeLocation()?:"" )
    
}