plugins {
    id("org.springframework.boot") version "3.0.2"
    id("io.spring.dependency-management") version "1.1.0"
    id("java")
}

group = "org.dk.template"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

extra["springCloudAlibabaVersion"] = "2022.0.0.0-RC2"


repositories {
    maven { url = uri("https://maven.aliyun.com/repository/public") }
    maven { url = uri("https://maven.aliyun.com/repository/central") }
    mavenLocal()
    mavenCentral()
}

//顶层目录不参与springboot构建
tasks.named("bootJar") {
    enabled = false
}

subprojects {

    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "java")

    dependencies {
        implementation(platform("com.alibaba.cloud:spring-cloud-alibaba-dependencies:${property("springCloudAlibabaVersion")}"))
        implementation("org.springframework.boot:spring-boot-starter")
        implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }

    tasks.test {
        useJUnitPlatform()
    }

}




