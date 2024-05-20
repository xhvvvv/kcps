package com.wxfactory.kcps.preview.tabs

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.PreferencesSettings
import com.wxfactory.kcps.common.core.entity.FrpConfigCCompose
import com.wxfactory.kcps.common.core.repository.SettingService
import com.wxfactory.kcps.common.core.repository.StoreDao
import com.wxfactory.kcps.common.core.repository.impl.SettingServiceImpl
import com.wxfactory.kcps.common.screen.data.ScreenViewModel
import com.wxfactory.kcps.common.tabs.ServerSideScreen
import com.wxfactory.kcps.frpfun.entity.FrpConfigS
import java.util.prefs.Preferences

@Preview()
@Composable
fun ServerTabTest(){
    val shit = FrpConfigCCompose(FrpConfigS("127.0.0.1",10624))
        
    MaterialTheme {
        ServerSideScreen(null,shit)
    }
}