import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = false
jar.enabled = false

dependencies {
	implementation(project(":ea-common"))
	implementation(project(":ea-infra"))
	implementation(project(":ea-domain"))
	implementation(project(":ea-security"))

	implementation("org.springframework.boot:spring-boot-starter-aop")
	implementation("org.springframework.boot:spring-boot-configuration-processor")

//	implementation("org.springframework.boot:spring-boot-starter-web")
}

//springBoot {
//	mainClass.set("com.nbe3.api.EmergencyAssistantApplicationKt")
//}