plugins {
    kotlin("multiplatform") version "1.9.0" apply false
    id("org.jetbrains.compose") version "1.5.11" apply false
}
 

allprojects{
    repositories {
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
        maven("https://maven.aliyun.com/repository/central")
        maven("https://maven.aliyun.com/repository/gradle-plugin")
        
    }
}