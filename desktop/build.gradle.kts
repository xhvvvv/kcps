plugins {
    kotlin("jvm")  
    id("org.jetbrains.compose")
}

dependencies {
    implementation(project(":shared"))
    implementation(compose.desktop.currentOs)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
}
tasks.compileJava{
    this.options.encoding = "utf-8"
}
 
version = "1.0.0"
compose.desktop {
    
    application {
//        this.jvmArgs.add()
        mainClass = "com.wxfactory.kcps.desktop.MainKt"
        nativeDistributions {
            this.licenseFile .set(rootProject.file("LICENSE"))
            targetFormats(
                org.jetbrains.compose.desktop.application.dsl.TargetFormat.Dmg,
                org.jetbrains.compose.desktop.application.dsl.TargetFormat.Msi,
                org.jetbrains.compose.desktop.application.dsl.TargetFormat.Deb,
                org.jetbrains.compose.desktop.application.dsl.TargetFormat.Rpm
            )
            this.packageName = "Fcp"
            this.packageVersion = version.toString()
            windows{
                this.packageVersion = version.toString()
                this.iconFile.set(file("icon.svg"))
            }
        }
        buildTypes{
            release{
                proguard{
                    configurationFiles.from("proguard.pro")
                }
            }
        }
    }
}