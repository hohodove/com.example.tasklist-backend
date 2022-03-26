package com.example.controller

import com.example.controller.request.CreateTaskRequest
import com.example.controller.response.FindAllTasksResponse
import com.example.controller.response.FindTaskByIdResponse
import com.example.infrastructure.framework.configureRouting
import com.example.infrastructure.framework.configureSerialization
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class TaskControllerKtTest {

    @Test
    fun `タスクの作成、取得、削除ができること`() {

        val mapper = jacksonObjectMapper()
        mapper.findAndRegisterModules()

        withTestApplication({
            configureRouting()
            configureSerialization()
        }) {

            handleRequest(HttpMethod.Post, "/task") {
                addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())

                val createTaskRequest = CreateTaskRequest(
                    "タスク１"
                )

                val createTaskJsonBody = mapper.writeValueAsString(createTaskRequest)
                setBody(createTaskJsonBody)
            }.apply {
                    assertEquals(HttpStatusCode.OK, response.status())
                    assertEquals("The task is created.", response.content)
                }

            handleRequest(HttpMethod.Post, "/task") {
                addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())

                val createTaskRequest = CreateTaskRequest(
                    name = "タスク２"
                )

                val createTaskJsonBody = mapper.writeValueAsString(createTaskRequest)
                setBody(createTaskJsonBody)
            }.apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("The task is created.", response.content)
            }

            val taskIds: List<String>
            handleRequest(HttpMethod.Get, "/tasks").apply {
                assertEquals(HttpStatusCode.OK, response.status())

                val findAllTasksResponse = mapper.readValue<List<FindAllTasksResponse>>(response.content!!)
                assertEquals(2, findAllTasksResponse.size)

                taskIds = findAllTasksResponse
                    .map { it.id }
            }

            handleRequest(HttpMethod.Get, "/task/${taskIds[0]}").apply {
                assertEquals(HttpStatusCode.OK, response.status())

                val findTaskByIdResponse = mapper.readValue<FindTaskByIdResponse>(response.content!!)
                assertTrue(findTaskByIdResponse.name != null)
            }

            handleRequest(HttpMethod.Delete, "/task/${taskIds[0]}").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("The task is deleted.", response.content)
            }

            handleRequest(HttpMethod.Delete, "/task/${taskIds[1]}").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("The task is deleted.", response.content)
            }

            handleRequest(HttpMethod.Get, "/tasks").apply {
                assertEquals(HttpStatusCode.OK, response.status())

                val findAllTasksResponse = mapper.readValue<List<FindAllTasksResponse>>(response.content!!)
                assertEquals(0, findAllTasksResponse.size)
            }

        }
    }
}
