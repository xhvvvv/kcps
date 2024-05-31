package com.wxfactory.kcps.common.util

import cn.hutool.setting.dialect.Props
import java.nio.charset.StandardCharsets
import java.util.*

val i18N : Properties by lazy {
     Props.getProp("in18-Zn.properties", StandardCharsets.UTF_8).toProperties()
}
val someStatic : Properties by lazy {
     Props.getProp("pro.properties", StandardCharsets.UTF_8).toProperties()
//     Props.getProp("test.properties", StandardCharsets.UTF_8).toProperties()
}