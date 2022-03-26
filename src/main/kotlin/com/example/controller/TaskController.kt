package com.example.controller

import com.example.controller.request.CreateTaskRequest
import com.example.controller.request.UpdateTaskRequest
import com.example.usecase.TaskUseCase
import com.example.usecase.dto.TaskUseCaseDto
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.taskController() {

    val taskUseCase = TaskUseCase()

    get("/tasks") {
        taskUseCase.findAll()
            .let { call.respond(it) }
    }

    get("/task/{taskId}") {
        // パスパラメータを指定しないパス(/task/)場合、以下のIllegalArgumentExceptionではなく、404 NotFoundとなる。
        val pathParameter = call.parameters["taskId"] ?: throw IllegalArgumentException("Path parameter is null.")

        taskUseCase.findById(pathParameter)
            ?.let { call.respond(it)}
            ?: call.respond("Not Found")
    }

    post("/task") {
        val body = call.receive<CreateTaskRequest>()

        requireNotNull(body.name) {"タスク名は必須項目です。"}

        val taskUseCaseDto = TaskUseCaseDto(
            name = body.name,
            dueDate = body.duedate
        )

        taskUseCase.create(taskUseCaseDto)
        call.respond("The task is created.")
    }

    put("/task/{taskId}") {
        // パスパラメータを指定しないパス(/task/)場合、以下のIllegalArgumentExceptionではなく、404 NotFoundとなる。
        val pathParameter = call.parameters["taskId"] ?: throw IllegalArgumentException("Path parameter is null.")

        val body = call.receive<UpdateTaskRequest>()
        val taskUseCaseDto = TaskUseCaseDto(
            pathParameter,
            body.name,
            body.status,
            body.dueDate
        )

        taskUseCase.update(taskUseCaseDto)
        call.respond("The task is updated.")
    }

    delete("/task/{taskId}") {
        // パスパラメータを指定しないパス(/task/)場合、以下のIllegalArgumentExceptionではなく、404 NotFoundとなる。
        val pathParameter = call.parameters["taskId"] ?: throw IllegalArgumentException("Path parameter is null.")

        taskUseCase.remove(pathParameter)
        call.respond("The task is deleted.")
    }
}
