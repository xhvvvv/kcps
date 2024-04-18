package com.wxfactory.kcps.common.core.moudle
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.navigator.tab.Tab
import com.wxfactory.kcps.common.core.entity.FrpConfigCCompose
import com.wxfactory.kcps.common.core.repository.MaybePersisRedis
import com.wxfactory.kcps.common.core.repository.SettingService
import com.wxfactory.kcps.common.core.repository.SettingServiceImpl
import com.wxfactory.kcps.common.screen.data.ScreenViewModel
import com.wxfactory.kcps.common.tabs.AboutTab
import com.wxfactory.kcps.common.tabs.ConfigTab
import com.wxfactory.kcps.common.tabs.SettingTab
import com.wxfactory.kcps.frpfun.entity.FrpConfig
import com.wxfactory.kcps.frpfun.entity.FrpConfigC
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.dsl.single
import org.koin.java.KoinJavaComponent.inject

val tables = module {
    single<List<Tab>>(named("tabs")) {
        listOf(
            ConfigTab(),
            AboutTab(),
            SettingTab(),
            
        )
    }
}
val data = module {
    single<MaybePersisRedis> { MaybePersisRedis(get()) }
    single<SettingService> { SettingServiceImpl(get()) }
    single<ScreenViewModel> { ScreenViewModel(get()) }
}
val frp = module {
    //全局frpc配置列表
    single<MutableList<FrpConfigCCompose<FrpConfigC>>> (named("fcs")) {
        val screenViewModel : ScreenViewModel = get()
        screenViewModel.fcs.map {
            FrpConfigCCompose(it)
        }.toMutableList()
    }
}

//必须放最底下
val common = module{ includes(tables , data , frp)}