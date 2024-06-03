import com.wxfactory.plugins.kotlinp.version.VersionPluginExtension

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
buildscript{
    repositories{
        mavenLocal()
        mavenCentral()
       
    }
    dependencies{
        classpath(files(rootProject.file("lib/KotlinPlugin-1.0.0.jar")))
    }
}

apply(plugin = "com.wxfactory.plugins.kotlinp.version.VersionContronKt")



version = "1.0.6"

compose{
    
    desktop {
        application {
//        this.jvmArgs.add()
            mainClass = "com.wxfactory.kcps.desktop.MainKt"
            this.javaHome="D:\\Programmer\\JDK\\jdk-17_windows-x64_bin\\jdk-17.0.1"
            nativeDistributions {
                val finalVersion = project.extensions.getByType(VersionPluginExtension::class).version.get()
                this.licenseFile .set(rootProject.file("LICENSE"))
                this.packageName = "Kcps"
                this.packageVersion = finalVersion
//                this.version  = project.extensions.getByType(VersionPluginExtension::class).version.get()
                this.description = "Frp Control Panel"
                this.copyright = "© 2022 My Name. All rights reserved."
                targetFormats(
                    org.jetbrains.compose.desktop.application.dsl.TargetFormat.Msi,
                    org.jetbrains.compose.desktop.application.dsl.TargetFormat.Exe,
                    org.jetbrains.compose.desktop.application.dsl.TargetFormat.Deb,
                    org.jetbrains.compose.desktop.application.dsl.TargetFormat.Rpm
                )
                modules(
                    "java.compiler",
                    "java.instrument",
                    "java.management",
                    "java.naming",
                    "java.prefs",
                    "java.scripting",
                    "java.sql",
                    "jdk.httpserver",
                    "jdk.unsupported"
                )
               
                windows{
                    this.packageVersion =  finalVersion
                    this.exePackageVersion = finalVersion
                    this.msiPackageVersion = finalVersion
                    this.iconFile.set(file("icon.svg"))
                    this.menuGroup = "menuGroup-xhvvvv"
                    this.installationPath  // 安装目录
                    this.shortcut
                    
                    this.upgradeUuid = "5ac63736-d8c7-4a65-1235-6870df88ddfe"
                    this.perUserInstall = true //为每个用户独特安装
                    this.dirChooser = true //自定义安装路径
                }
                
                linux{
                    this.packageVersion =    finalVersion
                    this.debPackageVersion = finalVersion
                    this.rpmPackageVersion = finalVersion
                    this.iconFile.set(file("icon.svg"))
                    this.menuGroup = "menuGroup-xhvvvv"
                    this.installationPath
                    this.shortcut
                    
                    this.packageName = "kcps-linux" 
                    this.debMaintainer = "wj1939146725@gmail.com"  
                    this.appRelease = "1" //rpm 包的发布值，或 deb 包的修订值；
                    this.appCategory = "CATEGORY" //rpm 包的组值，或 deb 包的部分值；
                    this.rpmLicenseType = "TYPE_OF_LICENSE" //rpm 包的一种许可证；
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


  
}