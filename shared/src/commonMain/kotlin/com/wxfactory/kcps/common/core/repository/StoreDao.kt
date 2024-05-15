package com.wxfactory.kcps.common.core.repository
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.coroutines.*
import com.russhwolf.settings.set
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.Serializable

class  StoreDao constructor(private val observableSettings: ObservableSettings) {
    companion object {
        const val APP_THEME = "app_theme_key"
        const val EXE_LOCATION = "exeLocation"
        const val EXE_CONFIG_TYPE = "INI"
        const val FCC_ALL = "allFcc"
    } 
    fun setString(key: String, value: String) {
        observableSettings.set(key = key, value = value)
    }
    fun getNonFlowString(key: String) = observableSettings.getString(
        key = key,
        defaultValue = "",
    )

    fun getString(key: String) = observableSettings.getStringOrNullFlow(key = key)

    fun setInt(key: String, value: Int) {
        observableSettings.set(key = key, value = value)
    }

    fun getInt(key: String) = observableSettings.getIntOrNullFlow(key = key)

    fun getIntFlow(key: String) = observableSettings.getIntFlow(key = key, defaultValue = 0)

   

    fun clearPreferences() {
        observableSettings.clear()
    }

    fun getBoolean(key: String): Flow<Boolean> {
        return observableSettings.getBooleanFlow(
            key = key,
            defaultValue = false,
        )
    }

    fun setBoolean(key: String, value: Boolean) {
        observableSettings.set(key = key, value = value)
    }

    fun getLong(key: Any): Flow<Long?> {
        return observableSettings.getLongFlow(
            key = key.toString(),
            defaultValue = 0,
        )
    }

    fun setLong(key: String, value: Long) {
        observableSettings.set(key = key, value = value)
    }
}
