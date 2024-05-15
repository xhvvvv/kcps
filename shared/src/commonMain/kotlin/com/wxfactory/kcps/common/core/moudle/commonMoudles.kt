package com.wxfactory.kcps.common.core.moudle
import cafe.adriel.voyager.navigator.tab.Tab
import com.wxfactory.kcps.common.core.entity.FrpConfigCCompose
import com.wxfactory.kcps.common.core.repository.FccService
import com.wxfactory.kcps.common.core.repository.SettingService
import com.wxfactory.kcps.common.core.repository.StoreDao
import com.wxfactory.kcps.common.core.repository.impl.FccServiceImpl
import com.wxfactory.kcps.common.core.repository.impl.SettingServiceImpl
import com.wxfactory.kcps.common.screen.data.ScreenViewModel
import com.wxfactory.kcps.common.tabs.AboutTab
import com.wxfactory.kcps.common.tabs.ConfigTab
import com.wxfactory.kcps.common.tabs.SettingTab
import com.wxfactory.kcps.frpfun.entity.FrpConfigC
import org.koin.core.qualifier.named
import org.koin.dsl.module

val tables = module {
    single<List<Tab>>(named("tabs")) {
        listOf(
            ConfigTab(),
            SettingTab(),
            AboutTab(),
        )
    }
}
val data = module {
    single<StoreDao> { StoreDao(get()) }
    single<SettingService> { SettingServiceImpl(get()) }
    single<FccService> { FccServiceImpl(get()) }
    single<ScreenViewModel> { ScreenViewModel(get(),get()) }
}
val frp = module {
    //全局frpc配置列表
    single<MutableList<FrpConfigCCompose<FrpConfigC>>> (named("fcs")) {
        val screenViewModel : ScreenViewModel = get()
        if (screenViewModel.fcs==null)
            mutableListOf<FrpConfigCCompose<FrpConfigC>>()
        else
            screenViewModel.fcs.map {
                FrpConfigCCompose(it)
            }.toMutableList()
    }
}

//必须放最底下
val common = module{ includes(tables , data , frp)}