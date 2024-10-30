dependencies {
    implementation(project(":ea-domain"))
    implementation(project(":ea-infra"))
    implementation(project(":ea-common"))
    implementation(project(":ea-security"))

    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2")

    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.springframework.boot:spring-boot-configuration-processor")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
}
