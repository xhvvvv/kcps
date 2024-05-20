package com.wxfactory.kcps.common.screen.data

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import cn.hutool.core.io.FileUtil
import cn.hutool.core.util.SerializeUtil
import com.wxfactory.kcps.common.core.repository.FccService
import com.wxfactory.kcps.common.core.repository.SettingService
import com.wxfactory.kcps.frpfun.entity.FrpConfigC
import com.wxfactory.kcps.frpfun.entity.FrpConfigS
import kotlinx.coroutines.flow.*
import java.io.File
import java.util.ArrayList

class ScreenViewModel(
    private val settingsRepository: SettingService,
    private val fccService: FccService ,
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
//    private final val fcsStorge :String = "fcs.list"
//    val fcs : MutableList<FrpConfigC> by lazy {
//        val f = File(fcsStorge)
//        if (FileUtil.exist(f)){
//            val shit = SerializeUtil.deserialize<MutableList<FrpConfigC> >(FileUtil.readBytes(f))
//            shit
//        }else
//            mutableListOf()
//    }
//    fun save( fc : List<FrpConfigC>) {
//        val f = File(fcsStorge)
//        val array = ArrayList<FrpConfigC>().apply {
//            this.addAll(fc)
//        }
//        val fcsS  = SerializeUtil.serialize(array)
//        FileUtil.writeBytes(fcsS,f)
//    }

    val fcs : List<FrpConfigC>? = fccService.getAllFcc() 
    fun save( fc : FrpConfigC) {
        fccService.saveOrUpdateFcc(fc)
    }
    fun save( fc : List<FrpConfigC>) {
        fccService.saveAllFcc(fc)
    }
    
    val fcServer : FrpConfigS? = null

}