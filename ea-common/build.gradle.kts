import org.springframework.boot.gradle.tasks.bundling.BootJar

val bootJar: BootJar by tasks
bootJar.enabled = false

dependencies {
	api("org.springframework.boot:spring-boot-starter-json")
	implementation("org.springframework:spring-context")
}