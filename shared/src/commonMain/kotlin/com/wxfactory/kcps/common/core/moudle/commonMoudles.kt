package com.wxfactory.kcps.common.core.moudle
import cafe.adriel.voyager.core.model.ScreenModel
import com.wxfactory.kcps.common.core.repository.MaybePersisRedis
import com.wxfactory.kcps.common.core.repository.SettingService
import com.wxfactory.kcps.common.core.repository.SettingServiceImpl
import com.wxfactory.kcps.common.screen.data.ScreenViewModel
import org.koin.dsl.module
val common = module{
    single<MaybePersisRedis> { MaybePersisRedis(get()) }
    single<SettingService> { SettingServiceImpl(get()) }
    single<ScreenViewModel> { ScreenViewModel(get()) }
}