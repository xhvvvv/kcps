plugins {
    kotlin("multiplatform") version "1.9.21" apply false
    id("org.jetbrains.compose") version "1.5.11" apply false
    id("com.diffplug.spotless") version "6.25.0"
    kotlin("plugin.serialization") version "1.9.21"  apply false
    
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

subprojects{
    apply(plugin = "com.diffplug.spotless")
    apply(plugin = "idea")
    spotless {
        kotlin {
            target("**/*.kt") //项目中的所有kt文件
//            ktlint().userData(mapOf("disabled_rules" to "filename")) //传递一些环境变量
//            licenseHeaderFile( //在正则是匹配到的位置插入头部声明
//                rootProject.file("${project.rootDir}/spotless/copyright.kt"),
//                "^(package|object|import|interface)",
//            )
            trimTrailingWhitespace() //移除每行末尾的尾随空格
            endWithNewline() //文件的结尾一定是换行符
        }
        
        java{
            trimTrailingWhitespace() //移除每行末尾的尾随空格
            endWithNewline() //文件的结尾一定是换行符
        }
//        format("kts") {
//            target("**/*.kts")
//            targetExclude("$buildDir/**/*.kts")
//            licenseHeaderFile(rootProject.file("spotless/copyright.kt"), "(^(?![\\/ ]\\*).*$)")
//        }
        format("misc") {
            target("**/*.md", "**/.gitignore")
            trimTrailingWhitespace()
            indentWithTabs()
            endWithNewline()
        }
    }
}