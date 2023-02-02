import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.0.2"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"
    kotlin("plugin.jpa") version "1.7.22"
}

group = "com.ndy"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-websocket")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation ("io.github.microutils:kotlin-logging-jvm:3.0.2")

    // step 2
    implementation ("org.springframework.boot:spring-boot-starter-freemarker")
    implementation ("org.springframework.boot:spring-boot-devtools")
    implementation ("org.webjars.bower:bootstrap:4.3.1")
    implementation ("org.webjars.bower:vue:2.5.16")
    implementation ("org.webjars.bower:axios:0.17.1")
    implementation ("org.webjars:sockjs-client:1.1.2")
    implementation ("org.webjars:stomp-websocket:2.3.3-1")

    //jpa
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly ("com.h2database:h2")


    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
