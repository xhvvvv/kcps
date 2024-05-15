package com.wxfactory.kcps.common.core.repository.impl

import com.alibaba.fastjson2.JSONArray
import com.alibaba.fastjson2.JSONObject
import com.wxfactory.kcps.common.core.repository.SettingService
import com.wxfactory.kcps.common.core.repository.StoreDao
import com.wxfactory.kcps.frpfun.entity.FrpConfig
import kotlinx.coroutines.flow.Flow

class SettingServiceImpl(private val maybePersisRedis: StoreDao): SettingService { 
    override suspend fun saveAppTheme(theme: Int) {
        maybePersisRedis.setInt(key = StoreDao.APP_THEME, value = theme)
    }

    override fun getAppTheme(): Flow<Int?> {
        return maybePersisRedis.getInt(key = StoreDao.APP_THEME)
    }

    override fun getExeLocation(): String? {
        return maybePersisRedis.getNonFlowString(key = StoreDao.EXE_LOCATION)
    }

    override fun saveExeLocation(file:String?) {
        file?.let {
            maybePersisRedis.setString(StoreDao.EXE_LOCATION, file )
        }
    }

    override fun getFccTypes(): Flow<String?> {
        return maybePersisRedis.getString(key = StoreDao.EXE_CONFIG_TYPE)
    }

    override fun saveFccTypes(type: String?) {
        type?.let {
            maybePersisRedis.setString(StoreDao.EXE_CONFIG_TYPE, type )
        }
    }


}