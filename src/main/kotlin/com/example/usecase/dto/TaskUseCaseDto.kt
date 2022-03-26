package com.example.usecase.dto

import com.example.domain.model.task.value_object.DueDate
import com.example.domain.model.task.entity.Task
import com.example.domain.model.task.value_object.TaskName
import java.time.LocalDate

data class TaskUseCaseDto(
    val id: String? = null,
    val name: String? = null,
    val status: String? = null,
    val dueDate: LocalDate? = null
) {

    fun createDomain(): Task {
        requireNotNull(name) {"タスク名は必須項目です。"}

        return Task.create(
            TaskName.valueOf(name),
            DueDate.valueOf(dueDate)
        )
    }

    companion object {
        fun domainToDto(task: Task): TaskUseCaseDto = TaskUseCaseDto(
            task.taskId.value(),
            task.taskName.value(),
            task.taskStatus.toString(),
            task.dueDate.value()
        )
    }
}
