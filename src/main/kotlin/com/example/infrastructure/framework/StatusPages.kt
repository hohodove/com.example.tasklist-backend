package com.example.infrastructure.framework

import com.example.controller.response.ErrorMessageResponse
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*

fun Application.configureStatusPages() {
    install(StatusPages) {

        exception<com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException> { cause ->
            call.respond(
                HttpStatusCode.BadRequest,
                ErrorMessageResponse(HttpStatusCode.BadRequest.value.toString(), "Request format is incorrect.")
            )
            throw cause
        }

        exception<IllegalArgumentException> { cause ->
            call.respond(
                HttpStatusCode.BadRequest,
                ErrorMessageResponse(HttpStatusCode.BadRequest.value.toString(), cause.message!!)
            )
            throw cause
        }

        exception<Throwable> { cause ->
            call.respond(HttpStatusCode.InternalServerError, "Internal Server Error")
            throw cause
        }
    }
}
