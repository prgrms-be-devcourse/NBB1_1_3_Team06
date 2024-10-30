package com.nbe3.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/health")
class HealthCheckApi {

    @GetMapping
    fun healthCheck(): String {
        return "health check success"
    }
}