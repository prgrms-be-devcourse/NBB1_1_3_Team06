import org.springframework.boot.gradle.tasks.bundling.BootJar

val bootJar: BootJar by tasks

bootJar.enabled = false

dependencies {
	implementation(project(":ea-common"))
	implementation(project(":ea-domain"))

	implementation("org.springframework.boot:spring-boot-starter-data-redis")

	//open feign
	implementation("org.springframework.cloud:spring-cloud-starter-openfeign:4.1.3")
	implementation("org.springframework.cloud:spring-cloud-commons:4.1.4")
}