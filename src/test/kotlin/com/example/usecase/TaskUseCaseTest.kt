package com.example.usecase

import com.example.infrastructure.framework.setupConfig
import com.example.test_util.KoinTestConfig
import com.example.usecase.dto.TaskUseCaseDto
import io.ktor.config.*
import io.ktor.server.testing.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

internal class TaskUseCaseTest {

    @JvmField
    @RegisterExtension
    val koinTestConfig = KoinTestConfig.set()

    val taskUseCase = TaskUseCase()

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
            val taskUseCaseDto1 = TaskUseCaseDto(
                name = "タスク１"
            )

            val taskUseCaseDto2 = TaskUseCaseDto(
                name = "タスク２"
            )

            taskUseCase.create(taskUseCaseDto1)
            taskUseCase.create(taskUseCaseDto2)
            val findAllResult = taskUseCase.findAll()
            assertEquals(2, findAllResult.size)
            assertEquals(taskUseCaseDto1.name, findAllResult[0].name)
            assertEquals(taskUseCaseDto2.name, findAllResult[1].name)

            val findById1 = taskUseCase.findById(findAllResult[0].id!!)
            assertEquals(taskUseCaseDto1.name, findById1?.name)

            taskUseCase.remove(findAllResult[0].id!!)
            taskUseCase.remove(findAllResult[1].id!!)
            assertEquals(0, taskUseCase.findAll().size)

        }
    }
}
