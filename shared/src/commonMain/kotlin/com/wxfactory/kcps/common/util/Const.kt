package com.wxfactory.kcps.common.util

import cn.hutool.setting.dialect.Props
import java.nio.charset.StandardCharsets
import java.util.*

val i18N : Properties by lazy {
     Props.getProp("in18-Zn.properties", StandardCharsets.UTF_8).toProperties()
}