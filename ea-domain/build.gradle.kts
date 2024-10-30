import org.springframework.boot.gradle.tasks.bundling.BootJar

val bootJar: BootJar by tasks

bootJar.enabled = false

plugins {
	kotlin("plugin.jpa") version "1.9.25"
}

apply(plugin = ("org.jetbrains.kotlin.plugin.jpa"))

dependencies {
	implementation(project(":ea-common"))
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	runtimeOnly("com.mysql:mysql-connector-j")
}