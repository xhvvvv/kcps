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
println("cao"+project.extensions.getByType(VersionPluginExtension::class).version.get())

compose{
    
    desktop {
        application {
        
//        this.jvmArgs.add()
            mainClass = "com.wxfactory.kcps.desktop.MainKt"
            this.javaHome="D:\\Programmer\\JDK\\jdk-17_windows-x64_bin\\jdk-17.0.1"
            nativeDistributions {
                this.licenseFile .set(rootProject.file("LICENSE"))
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
                this.packageName = "Fcp"
                this.packageVersion = project.extensions.getByType(VersionPluginExtension::class).version.get()
                windows{
                    this.packageVersion =  project.extensions.getByType(VersionPluginExtension::class).version.get()
                    this.iconFile.set(file("icon.svg"))
                    menuGroup = "menuGroup-xhvvvv"
                    upgradeUuid = "5ac63736-d8c7-4a65-1235-6870df88ddfe"
                }
                
                linux{
                    this.packageVersion =  project.extensions.getByType(VersionPluginExtension::class).version.get()
                    this.iconFile.set(file("icon.svg"))
                    menuGroup = "menuGroup-xhvvvv"
                    this.debPackageVersion = project.extensions.getByType(VersionPluginExtension::class).version.get()
                    this.rpmPackageVersion = project.extensions.getByType(VersionPluginExtension::class).version.get()
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