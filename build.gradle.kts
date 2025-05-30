plugins {
    java
    id("org.springframework.boot") version "3.4.4"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "ru.smartbudject"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator:3.4.4")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.4.4")
    implementation("org.springframework.boot:spring-boot-starter-security:3.4.4")
    implementation("org.springframework.boot:spring-boot-starter-web:3.4.4")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf:3.4.4")
    implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity6:3.1.3.RELEASE")
    implementation("org.liquibase:liquibase-core:4.29.2")
    implementation("org.mapstruct:mapstruct:1.6.3")
    implementation("org.mapstruct:mapstruct-processor:1.6.3")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.6")
    implementation("io.swagger.core.v3:swagger-annotations-jakarta:2.2.30")
    implementation ("io.jsonwebtoken:jjwt-api:0.11.5")

    compileOnly("org.projectlombok:lombok:1.18.36")

    runtimeOnly("io.micrometer:micrometer-registry-prometheus")
    runtimeOnly("org.postgresql:postgresql:42.7.5")
    runtimeOnly ("io.jsonwebtoken:jjwt-impl:0.11.5")
    runtimeOnly ("io.jsonwebtoken:jjwt-jackson:0.11.5")

    annotationProcessor("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")

    testImplementation("org.springframework.boot:spring-boot-starter-test:3.4.4")
    testImplementation("org.springframework.boot:spring-boot-testcontainers:3.4.4")
    testImplementation("org.springframework.security:spring-security-test:6.4.4")
    testImplementation("org.testcontainers:junit-jupiter:1.20.6")
    testImplementation("org.testcontainers:postgresql:1.20.6")
    testImplementation("org.projectlombok:lombok:1.18.36")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.11.4")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
