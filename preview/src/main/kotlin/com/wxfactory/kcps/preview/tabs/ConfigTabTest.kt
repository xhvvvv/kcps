package com.wxfactory.kcps.preview.tabs

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import com.wxfactory.kcps.common.tabs.frpc.guideYou
import com.wxfactory.kcps.common.util.theme.MyMaterialTheme

@Preview( )
@Composable
fun ConfigTabTest(){
    MyMaterialTheme(false) {
        guideYou()
    }

}