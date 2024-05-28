package com.wxfactory.kcps.preview.tabs

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.wxfactory.kcps.common.tabs.aboutPanel

@Preview()
@Composable
fun AboutTabTest(){
    MaterialTheme {
        aboutPanel(null)
    }
    
}