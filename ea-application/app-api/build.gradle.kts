dependencies {
	implementation(project(":ea-common"))
	implementation(project(":ea-infra"))
	implementation(project(":ea-domain"))
	implementation(project(":ea-security"))

	implementation("org.springframework.boot:spring-boot-starter-aop")
	implementation("org.springframework.boot:spring-boot-configuration-processor")

	implementation("org.springframework.boot:spring-boot-starter-web")
}
