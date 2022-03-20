package com.example.usecase.dto

import com.example.domain.model.task.*
import java.time.LocalDate

data class TaskUseCaseDto(
    val id: String?,
    val name: String,
    val status: String?,
    val duedate: LocalDate
) {

    fun createDomain(): Task = Task.create(
        TaskName.valueOf(name),
        DueDate.reconstract(duedate)
    )

    companion object {
        fun domainToDto(task: Task): TaskUseCaseDto = TaskUseCaseDto(
            task.taskId.value(),
            task.taskName.value(),
            task.taskStatus.toString(),
            task.dueDate.value()
        )
    }
}
