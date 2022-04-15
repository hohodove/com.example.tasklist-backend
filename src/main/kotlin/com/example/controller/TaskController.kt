package com.example.controller

import com.example.controller.request.CreateTaskRequest
import com.example.controller.request.UpdateTaskRequest
import com.example.usecase.TaskUseCase
import com.example.usecase.dto.TaskUseCaseDto
import io.ktor.application.*
import io.ktor.http.*
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
        // パスパラメータを指定しないパス(/task/)場合、以下のIllegalArgumentExceptionではなく、
        // KtorのRouting機能にて404 NotFoundが返却されるため、taskIdがnullのチェックは不要。
        // （以下のバリデーション処理は構文の備忘として残しておく）
        // val pathParameter = call.parameters["taskId"] ?: throw IllegalArgumentException("Path parameter is null.")
        val taskId = call.parameters["taskId"]!!

        call.respond(taskUseCase.findById(taskId))
            // 以下は構文の備忘として残している。
            // ?: call.respond(mapOf("message" to "Not Found."))
    }

    post("/task") {
        val body = call.receive<CreateTaskRequest>()

        // リクエストに必須項目が含まれない場合、Jacksonのmapperにてエラーとなるため、
        // 必須項目のチェックは不要。
        // 以下は構文の備忘として残している。
        // requireNotNull(body.name) {"タスク名は必須項目です。"}

        val taskUseCaseDto = TaskUseCaseDto(
            name = body.name,
            dueDate = body.duedate
        )

        taskUseCase.create(taskUseCaseDto)

        call.respond(HttpStatusCode.OK)
    }

    put("/task/{taskId}") {
        // パスパラメータを指定しないパス(/task/)場合、以下のIllegalArgumentExceptionではなく、
        // KtorのRouting機能にて404 NotFoundが返却されるため、taskIdがnullのチェックは不要。
        val taskId = call.parameters["taskId"]!!

        val body = call.receive<UpdateTaskRequest>()
        val taskUseCaseDto = TaskUseCaseDto(
            taskId,
            body.name,
            body.status,
            body.dueDate
        )

        taskUseCase.update(taskUseCaseDto)

        call.respond(HttpStatusCode.OK)
    }

    delete("/task/{taskId}") {
        // パスパラメータを指定しないパス(/task/)場合、以下のIllegalArgumentExceptionではなく、
        // KtorのRouting機能にて404 NotFoundが返却されるため、taskIdがnullのチェックは不要。
        val taskId = call.parameters["taskId"]!!

        taskUseCase.remove(taskId)

        call.respond(HttpStatusCode.OK)
    }
}
