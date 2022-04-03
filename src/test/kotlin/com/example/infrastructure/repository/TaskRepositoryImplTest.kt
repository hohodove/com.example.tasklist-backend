package com.example.infrastructure.repository

import com.example.controller.request.CreateTaskRequest
import com.example.domain.model.task.entity.Task
import com.example.domain.model.task.value_object.DueDate
import com.example.domain.model.task.value_object.TaskId
import com.example.domain.model.task.value_object.TaskName
import com.example.domain.model.task.value_object.TaskStatus
import com.example.domain.repository.TaskRepository
import com.example.infrastructure.framework.*
import com.example.test_util.KoinTestConfig
import io.ktor.application.*
import io.ktor.config.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension
import org.koin.test.KoinTest
import org.koin.test.inject
import java.time.LocalDate

internal class TaskRepositoryImplTest : KoinTest {

    @JvmField
    @RegisterExtension
    val koinTestConfig = KoinTestConfig.set()

    val taskRepository by inject<TaskRepository>()

    @Test
    fun `タスクの保存、取得、削除ができる`() {
        withTestApplication({
            (environment.config as MapApplicationConfig).apply {
                put("ktor.dataSource.url", "jdbc:postgresql://localhost:5432/test")
                put("ktor.dataSource.username", "admin")
                put("ktor.dataSource.password", "password")
            }
            setupConfig()
        }) {

            val task1 = Task.reconstruct(
                TaskId.valueOf("1234abcd-56ef-78ab-90cd-123456efabcd"),
                TaskName.valueOf("タスク１"),
                TaskStatus.NOT_COMPLETED,
                DueDate.reconstruct(LocalDate.of(2001, 1, 1))
            )

            val task2 = Task.reconstruct(
                TaskId.valueOf("2345bcde-67fa-89bc-01de-234567fabcde"),
                TaskName.valueOf("タスク２"),
                TaskStatus.NOT_COMPLETED,
                DueDate.reconstruct(LocalDate.of(2002, 2, 2))
            )

            taskRepository.save(task1)
            taskRepository.save(task2)
            assertEquals(2, taskRepository.findAll().size)

            val selectedTask = taskRepository.findById(task1.taskId)
            assertEquals("タスク１", selectedTask?.taskName?.value())

            taskRepository.remove(task1.taskId)
            taskRepository.remove(task2.taskId)

            val tasks = taskRepository.findAll()
            assertEquals(0, tasks.size)
        }
    }

    @Test
    fun `取得結果が0件の場合にもエラーが発生しないこと`() {

        withTestApplication({
            (environment.config as MapApplicationConfig).apply {
                put("ktor.dataSource.url", "jdbc:postgresql://localhost:5432/test")
                put("ktor.dataSource.username", "admin")
                put("ktor.dataSource.password", "password")
            }
            setupConfig()
        }) {

            assertTrue(taskRepository.findAll().isEmpty())

            val notExistTaskId = TaskId.valueOf("1234abcd-56ef-78ab-90cd-123456efabcd")
            val result = taskRepository.findById(notExistTaskId)
            assertEquals(null, result)
        }
    }
}
