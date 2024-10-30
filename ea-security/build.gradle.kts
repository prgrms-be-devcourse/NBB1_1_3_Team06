import org.springframework.boot.gradle.tasks.bundling.BootJar

val bootJar: BootJar by tasks
bootJar.enabled = false

dependencies {
	implementation(project(":ea-common"))
	implementation(project(":ea-domain"))
}