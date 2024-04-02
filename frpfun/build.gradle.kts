plugins {
    `java-library` 
}

group = "com.wxfactroy"
version = "0.0.1"
 
dependencies{
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
    api("cn.hutool:hutool-all:5.8.26")
    implementation("org.jetbrains:annotations:24.1.0")

//    testImplementation("org.junit.jupiter:junit-jupiter:5.7.1")
//    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    
    testImplementation(platform("org.junit:junit-bom:5.10.2-SNAPSHOT"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}
repositories{
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots")
}
java{
    sourceCompatibility= JavaVersion.VERSION_11
}

tasks.test{
//    useJUnit() junit4
//    useTestNG()
    useJUnitPlatform() //junit5
    this.testLogging {
        this.showStandardStreams = true
        this.debug 
    }
}
tasks.compileJava{
    this.options.encoding = "UTF-8"
}