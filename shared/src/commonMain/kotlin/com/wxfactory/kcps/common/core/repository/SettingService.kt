package com.wxfactory.kcps.common.core.repository

import com.wxfactory.kcps.frpfun.translate.ConfigTypes
import kotlinx.coroutines.flow.Flow


/**
 * 软件设置服务
 */
interface SettingService {
    suspend fun saveAppTheme(theme: Int)
    fun getAppTheme(): Flow<Int?>
    fun getExeLocation(): String?
    fun saveExeLocation(file:String?)

    fun getExesLocation(): String?
    fun saveExesLocation(file:String?)

    fun getFccTypes(): Flow<String?>
    fun saveFccTypes(file:String?)


}