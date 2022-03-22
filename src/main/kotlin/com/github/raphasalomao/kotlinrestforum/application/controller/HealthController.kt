package com.github.raphasalomao.kotlinrestforum.application.controller

import com.github.raphasalomao.kotlinrestforum.application.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/health")
class HealthController(
    private val userService: UserService
) {

    @GetMapping
    fun healthCheck(): ResponseEntity<Any> {
        return ResponseEntity.ok(object {
            val online = true
        })
    }

    @GetMapping("/test")
    fun invalidate(): ResponseEntity<Any> {
        return ResponseEntity.ok(
            object {
                val test = "running"
            }
        )
    }

}