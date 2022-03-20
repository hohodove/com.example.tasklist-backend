package com.example.usecase

import com.example.domain.model.task.*
import com.example.usecase.dto.TaskUseCaseDto
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDate

internal class TaskUseCaseTest {

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
