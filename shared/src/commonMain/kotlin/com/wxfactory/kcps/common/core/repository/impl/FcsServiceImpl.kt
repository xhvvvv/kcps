package com.wxfactory.kcps.common.core.repository.impl

import androidx.compose.runtime.collectAsState
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.coroutines.getBooleanFlow
import com.russhwolf.settings.get
import com.russhwolf.settings.serialization.decodeValueOrNull
import com.russhwolf.settings.serialization.encodeValue
import com.wxfactory.kcps.common.core.repository.FccService
import com.wxfactory.kcps.common.core.repository.FcsService
import com.wxfactory.kcps.common.core.repository.StoreDao
import com.wxfactory.kcps.common.util.FrpConfigSSerial
import com.wxfactory.kcps.common.util.FrpConfigSerial
import com.wxfactory.kcps.common.util.someStatic
import com.wxfactory.kcps.frpfun.entity.FrpConfig
import com.wxfactory.kcps.frpfun.entity.FrpConfigC
import com.wxfactory.kcps.frpfun.entity.FrpConfigS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer

/***
 * 可能存在数据事务问题！
 */
class FcsServiceImpl(private val observableSettings: ObservableSettings) : FcsService {
    private final val fcsKey : String = someStatic.getProperty("storePrefix", "") + "FCS"

    override fun getServer(): FrpConfigS? {
        return observableSettings.decodeValueOrNull<FrpConfigS>(FrpConfigSSerial,fcsKey)
    }

    override fun saveFcs(fcs: FrpConfigS) {
        observableSettings.encodeValue<FrpConfigS>(FrpConfigSSerial,fcsKey,fcs)
    }

}