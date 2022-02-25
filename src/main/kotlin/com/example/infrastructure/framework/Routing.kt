package com.example.infrastructure.framework

import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.response.*
import com.example.controller.*

fun Application.configureRouting() {
    routing {
        helloController()
    }
}
