
plugins {
    id("org.springframework.boot") version "3.2.0"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "2.0.0"
    kotlin("plugin.spring") version "2.0.0"
    `maven-publish`
}

group = "com.shiviraj.iot"
version = "0.0.1"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

publishing {
    publications {
        create<MavenPublication>("gpr") {
            from(components["java"])
            groupId = "com.robotutor"
            artifactId = "mqtt-starter"
            version = "1.0.1"

            pom {
                name.set("Reactive Logging Starter")
                description.set("A reactive logging starter package")
                url.set("https://maven.pkg.github.com/IOT-echo-system/mqtt-starter")
            }
        }
    }

    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/IOT-echo-system/logging-starter")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
                password = project.findProperty("gpr.token") as String? ?: System.getenv("TOKEN")
            }
        }
    }
}


repositories {
    mavenCentral()
    maven {
        url = uri("https://maven.pkg.github.com/IOT-echo-system/robotutor-tech-utils")
        credentials {
            username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
            password = project.findProperty("gpr.token") as String? ?: System.getenv("TOKEN")
        }
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-autoconfigure")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("com.google.code.gson:gson:2.8.9")
    implementation("org.springframework.integration:spring-integration-mqtt:6.0.0")
    implementation("com.robotutor:robotutor-tech-utils:1.0.0")
    implementation("com.robotutor:logging-starter:1.0.2")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict", "-Xdebug")
    }
}


tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.named("bootJar") {
    enabled = false
}
