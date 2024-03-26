package com.wxfactory.kcps.common.platform

import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.Settings

expect class SettingDef {
    fun getSetting(): ObservableSettings
}