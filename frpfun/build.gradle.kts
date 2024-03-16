plugins {
    `java-library` 
}

group = "com.wxfactroy"
version = "0.0.1"
 
dependencies{
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
}

java{
    sourceCompatibility= JavaVersion.VERSION_11
}