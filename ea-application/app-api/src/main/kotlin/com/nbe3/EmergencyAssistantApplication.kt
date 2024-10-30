package com.nbe3

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["com.nbe3.api", "com.nbe3.domain", "com.nbe3.infra", "com.nbe3.security"])
class EmergencyAssistantApplication

fun main(args: Array<String>) {
	runApplication<EmergencyAssistantApplication>(*args)
}
