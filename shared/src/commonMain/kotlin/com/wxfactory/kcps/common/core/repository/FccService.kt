package com.wxfactory.kcps.common.core.repository

import com.wxfactory.kcps.frpfun.entity.FrpConfig
import com.wxfactory.kcps.frpfun.entity.FrpConfigC
import kotlinx.coroutines.flow.Flow

interface FccService {
    fun getAllFcc():List<FrpConfigC>?
    fun saveAllFcc(fccs : List<FrpConfigC>)
    fun getFccById(id:String):FrpConfigC?
    
    fun saveOrUpdateFcc( fc : FrpConfigC)
    fun removeFcc(id:String)


}