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
 
 
