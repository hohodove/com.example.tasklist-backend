package com.example.controller

import com.example.usecase.TaskUseCase
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.taskController() {

    val taskUseCase = TaskUseCase()

    get("/tasks") {
        taskUseCase.findAll()
            .let { call.respond(it) }
    }
}
