package com.wxfactory.kcps.common.core.repository.impl

import com.wxfactory.kcps.common.core.repository.SettingService
import com.wxfactory.kcps.common.core.repository.StoreDao
import com.wxfactory.kcps.frpfun.entity.FrpConfig
import kotlinx.coroutines.flow.Flow

class SettingServiceImpl(private val maybePersisRedis: StoreDao): SettingService { 
    override suspend fun saveAppTheme(theme: Int) {
        maybePersisRedis.setInt(key = SettingService.APP_THEME, value = theme)
    }

    override fun getAppTheme(): Flow<Int?> {
        return maybePersisRedis.getInt(key = SettingService.APP_THEME)
    }

    override fun getExeLocation(): String? {
        return maybePersisRedis.getNonFlowString(key = SettingService.EXE_LOCATION)
    }

    override fun saveExeLocation(file:String?) {
        file?.let {
            maybePersisRedis.setString(SettingService.EXE_LOCATION, file )
        }
    }

    override fun getExesLocation(): String? {
        return maybePersisRedis.getNonFlowString(key = SettingService.EXES_LOCATION)
    }

    override fun saveExesLocation(file: String?) {
        file?.let {
            maybePersisRedis.setString(SettingService.EXES_LOCATION, file )
        }
    }

    override fun getFccTypes(): Flow<String?> {
        return maybePersisRedis.getString(key = SettingService.EXE_CONFIG_TYPE)
    }

    override fun getFccTypeStr(): String? {
        return maybePersisRedis.getNonFlowString(key = SettingService.EXE_CONFIG_TYPE)
    }

    override fun saveFccTypes(type: String?) {
        type?.let {
            maybePersisRedis.setString(SettingService.EXE_CONFIG_TYPE, type )
        }
    }

    override suspend fun ifReadGuide(con: Boolean) {
        
    }

    override fun getIfReadGuide(): Flow<Boolean> {
        TODO("Not yet implemented")
    }


}