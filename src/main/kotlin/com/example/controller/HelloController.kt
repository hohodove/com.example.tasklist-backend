package com.example.controller

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.helloController() {
    get("/") {
        call.respondText("Hello World!")
    }

    get("/helloJson") {
        call.respond(mapOf("hello" to "world"))
    }
}
