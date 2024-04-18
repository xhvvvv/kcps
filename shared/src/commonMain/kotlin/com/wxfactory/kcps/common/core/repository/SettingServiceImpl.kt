package com.wxfactory.kcps.common.core.repository

import kotlinx.coroutines.flow.Flow

class SettingServiceImpl(private val maybePersisRedis:MaybePersisRedis):SettingService { 
    override suspend fun saveAppTheme(theme: Int) {
        maybePersisRedis.setInt(key = MaybePersisRedis.APP_THEME, value = theme)
    }

    override fun getAppTheme(): Flow<Int?> {
        return maybePersisRedis.getInt(key = MaybePersisRedis.APP_THEME)
    }

    override fun getExeLocation(): String? {
        return maybePersisRedis.getNonFlowString(key = MaybePersisRedis.EXE_LOCATION)
    }

    override fun saveExeLocation(file:String?) {
        file?.let {
            maybePersisRedis.setString( MaybePersisRedis.EXE_LOCATION , file )
        }
    }

    override fun getFccTypes(): Flow<String?> {
        return maybePersisRedis.getString(key = MaybePersisRedis.EXE_CONFIG_TYPE)
    }

    override fun saveFccTypes(type: String?) {
        type?.let {
            maybePersisRedis.setString( MaybePersisRedis.EXE_CONFIG_TYPE , type )
        }
    }

    override fun clearAll() {
        TODO("Not yet implemented")
    }

    override fun getSessionTime(): Flow<Int?> {
        TODO("Not yet implemented")
    }

    override fun getShortBreakTime(): Flow<Int?> {
        TODO("Not yet implemented")
    }

    override fun getLongBreakTime(): Flow<Int?> {
        TODO("Not yet implemented")
    }

    override fun getHourFormat(): Flow<Int?> {
        TODO("Not yet implemented")
    }

    override fun saveSessionTime(sessionTime: Int) {
        TODO("Not yet implemented")
    }

    override fun saveLongBreakTime(longBreakTime: Int) {
        TODO("Not yet implemented")
    }

    override fun saveHourFormat(timeFormat: Int) {
        TODO("Not yet implemented")
    }

    override fun saveShortBreakTime(shortBreakTime: Int) {
        TODO("Not yet implemented")
    }

    override fun shortBreakColor(): Flow<Long?> {
        TODO("Not yet implemented")
    }

    override fun saveShortBreakColor(color: Long) {
        TODO("Not yet implemented")
    }

    override fun longBreakColor(): Flow<Long?> {
        TODO("Not yet implemented")
    }

    override fun saveLongBreakColor(color: Long) {
        TODO("Not yet implemented")
    }

    override fun focusColor(): Flow<Long?> {
        TODO("Not yet implemented")
    }

    override fun saveFocusColor(color: Long) {
        TODO("Not yet implemented")
    }

    override fun saveUsername(value: String) {
        TODO("Not yet implemented")
    }

    override fun getUsername(): Flow<String?> {
        TODO("Not yet implemented")
    }

    override fun remindersOn(): Flow<Int?> {
        TODO("Not yet implemented")
    }

    override fun toggleReminder(value: Int) {
        TODO("Not yet implemented")
    }
}