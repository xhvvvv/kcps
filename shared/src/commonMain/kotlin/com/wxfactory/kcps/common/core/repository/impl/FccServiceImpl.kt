package com.wxfactory.kcps.common.core.repository.impl

import androidx.compose.runtime.collectAsState
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.coroutines.getBooleanFlow
import com.russhwolf.settings.get
import com.russhwolf.settings.serialization.decodeValueOrNull
import com.russhwolf.settings.serialization.encodeValue
import com.wxfactory.kcps.common.core.repository.FccService
import com.wxfactory.kcps.common.core.repository.StoreDao
import com.wxfactory.kcps.common.util.FrpConfigSerial
import com.wxfactory.kcps.frpfun.entity.FrpConfig
import com.wxfactory.kcps.frpfun.entity.FrpConfigC
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer

/***
 * 可能存在数据事务问题！
 */
class FccServiceImpl(private val observableSettings: ObservableSettings) : FccService {
    private final val allFcc : String = "ALL_FCC2"
    public final val ENABLE_PREFIX : String = "ENABLEPREFIX"
    override fun getAllFcc(): List<FrpConfigC>? {
        return observableSettings.decodeValueOrNull<List<FrpConfigC>>(ListSerializer(FrpConfigSerial),allFcc)
    }

    override fun saveAllFcc(fccs: List<FrpConfigC>) {
        observableSettings.encodeValue<List<FrpConfigC>>(ListSerializer(FrpConfigSerial),allFcc,fccs)
    }
    
    override fun getFccById(id:String): FrpConfigC? {
        val thisfcc = getAllFcc();
        return thisfcc?.find { 
            it.id === id
        }
    }

    override fun saveOrUpdateFcc(fc: FrpConfigC) {
        synchronized(this){
            var thisfcc = getAllFcc();
            val hasOne = thisfcc?.find {
                it.id === fc.id
            }
            if (hasOne == null){
                if (thisfcc==null) thisfcc = mutableListOf()
                thisfcc.toMutableList().let {
                    it.add(fc)
                    saveAllFcc(thisfcc)
                }
            }else{
                thisfcc?.toMutableList()?.let {
                    it.remove(hasOne)
                    it.add(fc)
                    saveAllFcc(thisfcc)
                }
            }
        }
    }

    override fun removeFcc(id: String) {
        synchronized(this) {
            var thisfcc = getAllFcc();
            val hasOne = thisfcc?.find {
                it.id === id
            }
            thisfcc?.toMutableList()?.let {
                it.remove(hasOne)
                saveAllFcc(thisfcc)
            }
        }
    }

}