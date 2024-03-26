package com.wxfactory.kcps.common.platform

import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.PreferencesSettings
import com.russhwolf.settings.Settings
import java.util.prefs.Preferences

actual class SettingDef {
    actual fun getSetting(): ObservableSettings {
        val delegate: Preferences = Preferences.userRoot()
        return PreferencesSettings(delegate)
    }
}