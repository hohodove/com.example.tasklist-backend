package com.example.usecase

import com.example.infrastructure.framework.koin_modules.taskRepositoryModule
import com.example.usecase.dto.TaskUseCaseDto
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension
import org.koin.core.logger.Level
import org.koin.test.junit5.KoinTestExtension

internal class TaskUseCaseTest {

    @JvmField
    @RegisterExtension
    val koinTestExtension = KoinTestExtension.create {

        //KoinのIssue #1188の通り、Ktor 1.6.0以降でKoinにてNoSuchMethodError例外が発生するため、
        // workaroundとして、ロガーのログレベルをERRORで設定している。
        printLogger(level = Level.ERROR)

        modules(taskRepositoryModule)
    }

    val taskUseCase = TaskUseCase()

    @Test
    fun `タスクの保存、取得、削除ができる`() {

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
