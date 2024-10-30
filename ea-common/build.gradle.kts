import org.springframework.boot.gradle.tasks.bundling.BootJar

//val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = false
//jar.enabled = true

dependencies {
	api("org.springframework.boot:spring-boot-starter-json")
	implementation("org.springframework:spring-context")
}