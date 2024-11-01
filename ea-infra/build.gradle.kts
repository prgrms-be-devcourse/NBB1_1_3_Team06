import org.springframework.boot.gradle.tasks.bundling.BootJar

val bootJar: BootJar by tasks

bootJar.enabled = false

dependencies {
    implementation(project(":ea-common"))
    implementation(project(":ea-domain"))

    implementation("org.springframework.boot:spring-boot-starter-data-redis")

    // open feign
    implementation(
        "org.springframework.cloud:spring-cloud-starter-openfeign:4.1.3"
    )
    implementation("org.springframework.cloud:spring-cloud-commons:4.1.4")

    // ai
    implementation(
        "org.springframework.ai:spring-ai-chroma-store-spring-boot-starter:1.0.0-M2"
    )
    implementation(
        "org.springframework.ai:spring-ai-openai-spring-boot-starter:1.0.0-M2"
    )

    // shedlock
    implementation("net.javacrumbs.shedlock:shedlock-spring:5.13.0")
    implementation(
        "net.javacrumbs.shedlock:shedlock-provider-redis-spring:5.13.0"
    )
}
