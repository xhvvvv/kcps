-dontwarn org.apache.commons.**
-dontwarn cn.hutool.**
-keep class kotlinx.coroutines.internal.MainDispatcherFactory { *; }
-keep class kotlinx.coroutines.swing.SwingDispatcherFactory { *; }
#使用反射构造了一些类，所以这里需要完整的保留类，否则运行时将会缺少相应的构造函数
-keep class com.wxfactory.kcps.frpfun.entity.** { *; }