package com.wxfactory.kcps.common.core.repository

import com.wxfactory.kcps.frpfun.entity.FrpConfig
import com.wxfactory.kcps.frpfun.entity.FrpConfigC
import com.wxfactory.kcps.frpfun.entity.FrpConfigS
import kotlinx.coroutines.flow.Flow

interface FcsService {
    fun getServer(): FrpConfigS?
    fun saveFcs(fcs : FrpConfigS)

}