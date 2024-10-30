plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25" apply false
	id("org.springframework.boot") version "3.3.5" apply false
	id("io.spring.dependency-management") version "1.1.6" apply false
}

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

allprojects {
	group = "com.nbe3"
	version = "0.0.1"

	repositories {
		mavenCentral()
	}
}

subprojects {

	apply {
		plugin("org.jetbrains.kotlin.jvm")
		plugin("org.jetbrains.kotlin.plugin.spring")
		plugin("org.springframework.boot")
		plugin("io.spring.dependency-management")
	}

	repositories {
		mavenCentral()
		maven {
			url = uri("https://repo.spring.io/milestone")
		}
	}

	dependencies {
		implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
		implementation("org.jetbrains.kotlin:kotlin-reflect")

		testImplementation("org.springframework.boot:spring-boot-starter-test")
		testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
		testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	}

	kotlin {
		compilerOptions {
			freeCompilerArgs.addAll("-Xjsr305=strict")
		}
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}
}

project(":ea-application") {
	tasks.getByName("bootJar") {
		enabled = false
	}

	tasks.getByName("jar") {
		enabled = false
	}
}