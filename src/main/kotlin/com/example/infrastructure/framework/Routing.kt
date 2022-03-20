package com.example.infrastructure.framework

import io.ktor.routing.*
import io.ktor.application.*
import com.example.controller.*

fun Application.configureRouting() {
    routing {
        helloController()
        taskController()
    }
}
