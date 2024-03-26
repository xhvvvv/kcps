package com.wxfactory.kcps.common.platform

import com.russhwolf.settings.ObservableSettings
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun moudleForPf(): Module = module {
    single<ObservableSettings> { SettingDef().getSetting() }
}