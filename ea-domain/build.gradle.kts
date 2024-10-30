import org.springframework.boot.gradle.tasks.bundling.BootJar

//val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = false
//jar.enabled = true

dependencies {
	implementation(project(":ea-common"))
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	runtimeOnly("com.mysql:mysql-connector-j")
}