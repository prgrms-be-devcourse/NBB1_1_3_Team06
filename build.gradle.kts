plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25" apply false
    id("org.springframework.boot") version "3.3.5" apply false
    id("io.spring.dependency-management") version "1.1.6" apply false
    id("com.diffplug.spotless") version "7.0.0.BETA4"

    kotlin("plugin.lombok") version "1.9.25" apply false
    kotlin("kapt") version "1.9.25"
    id("io.freefair.lombok") version "8.0.0" apply false
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

allprojects {
    group = "com.nbe2"
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
        plugin("com.diffplug.spotless")
        plugin("org.jetbrains.kotlin.kapt")
        plugin("io.freefair.lombok")
    }

    repositories {
        mavenCentral()
        maven {
            url = uri("https://repo.spring.io/milestone")
        }
    }

    dependencies {

        compileOnly("org.projectlombok:lombok")
        runtimeOnly("org.projectlombok:lombok")
        kapt("org.projectlombok:lombok")
        annotationProcessor("org.projectlombok:lombok")

        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")

        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")

        // Kotest
        testImplementation("io.kotest:kotest-runner-junit5:5.6.2")
        testImplementation("io.kotest:kotest-assertions-core:5.6.2")

        // MockK
        testImplementation("io.mockk:mockk:1.13.5")
    }

    kotlin {
        compilerOptions {
            freeCompilerArgs.addAll("-Xjsr305=strict")
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    spotless {
        kotlin {
            targetExclude("build/generated/**/*.kt")
            targetExclude("bin/**/*.kt")
            ktfmt("0.49").googleStyle().configure {
                it.setMaxWidth(80)
                it.setBlockIndent(4)
                it.setContinuationIndent(8)
                it.setRemoveUnusedImports(true)
            }

        }
    }

    kapt {
        keepJavacAnnotationProcessors = true
    }
}





tasks.register<Copy>("addGitPreCommitHook") {
    from("script/pre-commit")
    into(".git/hooks")
}


project(":ea-application") {
    tasks.getByName("bootJar") {
        enabled = false
    }

    tasks.getByName("jar") {
        enabled = false
    }
}
