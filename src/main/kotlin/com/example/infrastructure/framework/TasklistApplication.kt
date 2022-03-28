package com.example.infrastructure.framework

import io.ktor.application.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    configureRouting()
    configureLogging()
    configureSerialization()
    configureStatusPages()
}
