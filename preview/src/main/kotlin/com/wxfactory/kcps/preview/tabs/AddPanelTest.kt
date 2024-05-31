package com.wxfactory.kcps.preview.tabs

 
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.wxfactory.kcps.common.core.entity.FrpConfigCCompose
import com.wxfactory.kcps.common.tabs.frpc.AddPanel
import com.wxfactory.kcps.frpfun.entity.FrpConfigC

@Preview( )
@Composable
fun AddPanelTest(){
    val stateFcs  = remember { mutableListOf<FrpConfigCCompose<FrpConfigC>>() }
    val shit = AddPanel(stateFcs){ tfc ->
        stateFcs.add(FrpConfigCCompose(tfc))
    }
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        shit.Content()
    }
   
}