plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    kotlin("plugin.serialization")
}

group = "com.wxfactroy"
version = "1.0.0"
//
kotlin {
    jvm {
        jvmToolchain(17)
        withJava()
        testRuns.named("test") {
            executionTask.configure {
                useJUnitPlatform()
            }
        }
    }
//    js {
//        browser {
//            commonWebpackConfig {
//                cssSupport {
//                    enabled.set(true)
//                }
//            }
//        }
//    }
    val hostOs = System.getProperty("os.name")
    val isArm64 = System.getProperty("os.arch") == "aarch64"
    val isMingwX64 = hostOs.startsWith("Windows")
//    val nativeTarget = when {
//        hostOs == "Mac OS X" && isArm64 -> macosArm64("native")
//        hostOs == "Mac OS X" && !isArm64 -> macosX64("native")
//        hostOs == "Linux" && isArm64 -> linuxArm64("native")
//        hostOs == "Linux" && !isArm64 -> linuxX64("native")
//        isMingwX64 -> mingwX64("native")
//        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
//    }

    
    sourceSets {
        
        val commonMain by getting {
            
            dependencies{
                api(compose.foundation)
                api(compose.material)
                api(compose.material3)
                api(compose.preview)
                api(compose.runtime)
                api(compose.runtimeSaveable)
//                implementation(compose.components)
                //提供一些额外的图标
                api(compose.materialIconsExtended)
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
                api("org.jetbrains.kotlinx:kotlinx-coroutines-swing:1.7.3")
                /*compose lifecycle*/
//                api("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
                /** Koin */
                api(platform("io.insert-koin:koin-bom:3.5.3"))
                api("io.insert-koin:koin-core")
                api("io.insert-koin:koin-compose")

                /** settings */
                api("com.russhwolf:multiplatform-settings-no-arg:1.1.1")
                api("com.russhwolf:multiplatform-settings-coroutines:1.1.1")
                api("com.russhwolf:multiplatform-settings-serialization:1.1.1")
                
                /** voyager */
                val voyagerVersion = "1.0.0"
                api("cafe.adriel.voyager:voyager-navigator:$voyagerVersion")
                // Screen Model
                api("cafe.adriel.voyager:voyager-screenmodel:$voyagerVersion")
                // BottomSheetNavigator
                api("cafe.adriel.voyager:voyager-bottom-sheet-navigator:$voyagerVersion")
                // TabNavigator
                api("cafe.adriel.voyager:voyager-tab-navigator:$voyagerVersion")
                //依赖frp包
                api(project(":frpfun"))
                
                //验证包
                api("commons-validator:commons-validator:1.8.0")

                api(kotlin("reflect"))

                api("org.jetbrains.kotlinx:kotlinx-serialization-core:1.6.3")
            }
            
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting {
            
        }
        val jvmTest by getting
//        val jsMain by getting
//        val jsTest by getting
//        val nativeMain by getting
//        val nativeTest by getting

    }
}