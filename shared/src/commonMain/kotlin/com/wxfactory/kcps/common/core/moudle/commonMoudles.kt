package com.wxfactory.kcps.common.core.moudle
import cafe.adriel.voyager.navigator.tab.Tab
import com.wxfactory.kcps.common.core.entity.FrpConfigCCompose
import com.wxfactory.kcps.common.core.repository.FccService
import com.wxfactory.kcps.common.core.repository.FcsService
import com.wxfactory.kcps.common.core.repository.SettingService
import com.wxfactory.kcps.common.core.repository.StoreDao
import com.wxfactory.kcps.common.core.repository.impl.FccServiceImpl
import com.wxfactory.kcps.common.core.repository.impl.FcsServiceImpl
import com.wxfactory.kcps.common.core.repository.impl.SettingServiceImpl
import com.wxfactory.kcps.common.screen.data.ScreenViewModel
import com.wxfactory.kcps.common.tabs.*
import com.wxfactory.kcps.common.tabs.frpc.ConfigTab
import com.wxfactory.kcps.frpfun.entity.FrpConfigC
import com.wxfactory.kcps.frpfun.entity.FrpConfigS
import com.wxfactory.kcps.frpfun.util.CommonUtil
import org.koin.core.qualifier.named
import org.koin.dsl.module

val tables = module {
    single<List<Tab>>(named("tabs")) {
        listOf(
            ConfigTab(),
            ServerTab(),
            SettingTab(),
            AboutTab(),
//            TestTab(),
        )
    }
}
val data = module {
    single<StoreDao> { StoreDao(get()) }
    single<SettingService> { SettingServiceImpl(get()) }
    single<ScreenViewModel> { ScreenViewModel(get(),get(),get()) }
}

const val FC_C = "fcClients"
const val FC_S = "fcServer"
 
val frp = module {
    //fcc服务
    single<FccService> { FccServiceImpl(get()) }
    //fcs服务
    single<FcsService> { FcsServiceImpl(get()) }
    //全局frpc配置列表
    single<MutableList<FrpConfigCCompose<FrpConfigC>>> (named(FC_C)) {
        val screenViewModel : ScreenViewModel = get()
        if (screenViewModel.fcs==null)
            mutableListOf<FrpConfigCCompose<FrpConfigC>>()
        else
            screenViewModel.fcs.map {
                FrpConfigCCompose(it)
            }.toMutableList()
    }
    //全局frps
    single<FrpConfigCCompose<FrpConfigS>> (named(FC_S)) {
        val screenViewModel : ScreenViewModel = get()
        screenViewModel.fcServer?.let { 
            FrpConfigCCompose(it)
        }?: FrpConfigCCompose(
            FrpConfigS("127.0.0.1",10624).apply { 
                name = "sDefault" 
                id = CommonUtil.generateId()
            }
        )
    }
}

//必须放最底下
val common = module{ includes(tables , data , frp)}