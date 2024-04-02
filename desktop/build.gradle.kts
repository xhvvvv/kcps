plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
}

dependencies {
    implementation(project(":shared"))
    implementation(compose.desktop.currentOs)
}
tasks.compileJava{
    this.options.encoding = "utf-8"
}
 

compose.desktop {
    
    application {
//        this.jvmArgs.add()
        mainClass = "com.wxfactory.kcps.desktop.MainKt"
        nativeDistributions {
            targetFormats(
                org.jetbrains.compose.desktop.application.dsl.TargetFormat.Dmg,
                org.jetbrains.compose.desktop.application.dsl.TargetFormat.Msi,
                org.jetbrains.compose.desktop.application.dsl.TargetFormat.Deb,
                org.jetbrains.compose.desktop.application.dsl.TargetFormat.Rpm
            )
            packageName = "frpClient"
            packageName = "1.0.0"
        }
        
    }
    
}
