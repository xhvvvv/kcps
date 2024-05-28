package com.wxfactory.kcps.common.core.repository

import com.wxfactory.kcps.common.util.someStatic
import com.wxfactory.kcps.frpfun.translate.ConfigTypes
import kotlinx.coroutines.flow.Flow


/**
 * 软件设置服务
 */
interface SettingService {
    companion object {
        val APP_THEME =          someStatic.getProperty("storePrefix", "")+"app_theme_key"
        val EXE_LOCATION =       someStatic.getProperty("storePrefix", "")+"exeLocation"
        val EXES_LOCATION =      someStatic.getProperty("storePrefix", "")+"exesLocation"
        val EXE_CONFIG_TYPE =    someStatic.getProperty("storePrefix", "")+"INI"
        val FCC_ALL =            someStatic.getProperty("storePrefix", "")+"allFcc"
        val READ_GUIDE =         someStatic.getProperty("storePrefix", "")+"readuide"  
    }
    suspend fun saveAppTheme(theme: Int)
    fun getAppTheme(): Flow<Int?>
    fun getExeLocation(): String?
    fun saveExeLocation(file:String?)

    fun getExesLocation(): String?
    fun saveExesLocation(file:String?)

    fun getFccTypes(): Flow<String?>
    fun saveFccTypes(file:String?)

    suspend fun ifReadGuide(con : Boolean)
    fun getIfReadGuide() : Flow<Boolean>
}