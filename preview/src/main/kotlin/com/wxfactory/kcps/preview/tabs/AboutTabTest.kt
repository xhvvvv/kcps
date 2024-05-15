package com.wxfactory.kcps.preview.tabs

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.wxfactory.kcps.common.core.entity.FrpConfigCCompose
import com.wxfactory.kcps.common.core.repository.SettingService
import com.wxfactory.kcps.common.screen.data.ScreenViewModel
import com.wxfactory.kcps.common.tabs.AddPanel
import com.wxfactory.kcps.common.tabs.aboutPanel
import com.wxfactory.kcps.frpfun.entity.FrpConfigC

@Preview()
@Composable
fun AboutTabTest(){
    MaterialTheme {
        aboutPanel(null)
    }
    
}