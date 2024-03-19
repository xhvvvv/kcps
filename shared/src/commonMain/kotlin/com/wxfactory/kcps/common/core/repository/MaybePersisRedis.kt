package com.wxfactory.kcps.common.core.repository
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.coroutines.getBooleanFlow
import com.russhwolf.settings.coroutines.getIntFlow
import com.russhwolf.settings.coroutines.getIntOrNullFlow
import com.russhwolf.settings.coroutines.getLongFlow
import com.russhwolf.settings.coroutines.getStringOrNullFlow
import com.russhwolf.settings.set
import kotlinx.coroutines.flow.Flow

class MaybePersisRedis constructor(private val observableSettings: ObservableSettings) {
    companion object {
        const val NOTIFICATION_OPTION = "notification_option_key"
        const val USERNAME = "username_key"
        const val SHORT_BREAK_COLOR = "short_break_color_key"
        const val LONG_BREAK_COLOR = "long_break_color_key"
        const val FOCUS_COLOR = "focus_color_key"
        const val APP_THEME = "app_theme_key"
        const val FOCUS_TIME = "focus_time_key"
        const val SHORT_BREAK_TIME = "short_break_time_key"
        const val LONG_BREAK_TIME = "long_break_time_key"
        const val HOUR_FORMAT = "hour_format_key"
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
