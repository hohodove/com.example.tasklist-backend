package com.example.infrastructure.framework

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.request.*
import org.slf4j.event.Level

fun Application.configureLogging() {
    install(CallLogging) {
        level = Level.INFO
        format { call ->
            val status = call.response.status()
            val httpMethod = call.request.httpMethod.value
            val requestPath = call.request.path()
            val queryString = call.request.queryString()
            val cookies = call.request.cookies.rawCookies
            "Status: $status, HTTP method: $httpMethod, RequestPath: $requestPath, " +
                    "QueryString: $queryString, Cookies: $cookies"
        }
    }
}
