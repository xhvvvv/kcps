plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

group = "com.wxfactroy"
version = "0.1.0"
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
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.material3)
//                implementation(compose.preview)
                implementation(compose.runtime)
                implementation(compose.runtimeSaveable)
//                implementation(compose.components)
                //提供一些额外的图标
                implementation(compose.materialIconsExtended)
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing:1.7.3")
                /*compose lifecycle*/
//                api("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
                /** Koin */
                api(platform("io.insert-koin:koin-bom:3.5.3"))
                api("io.insert-koin:koin-core")
                api("io.insert-koin:koin-compose")

                /** settings */
                api("com.russhwolf:multiplatform-settings-no-arg:1.1.1")
                api("com.russhwolf:multiplatform-settings-coroutines:1.1.1")
                
                /** voyager */
                val voyagerVersion = "1.0.0"
                implementation("cafe.adriel.voyager:voyager-navigator:$voyagerVersion")
                // Screen Model
                implementation("cafe.adriel.voyager:voyager-screenmodel:$voyagerVersion")
                // BottomSheetNavigator
                implementation("cafe.adriel.voyager:voyager-bottom-sheet-navigator:$voyagerVersion")
                // TabNavigator
                implementation("cafe.adriel.voyager:voyager-tab-navigator:$voyagerVersion")
                //依赖frp包
                api(project(":frpfun"))
               

                api(kotlin("reflect"))
            }
            
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting
        val jvmTest by getting
//        val jsMain by getting
//        val jsTest by getting
//        val nativeMain by getting
//        val nativeTest by getting
    }
}
