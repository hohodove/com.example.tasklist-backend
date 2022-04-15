package com.example.usecase

import com.example.domain.model.task.value_object.DueDate
import com.example.domain.model.task.value_object.TaskId
import com.example.domain.model.task.value_object.TaskName
import com.example.domain.model.task.value_object.TaskStatus
import com.example.domain.repository.TaskRepository
import com.example.usecase.dto.TaskUseCaseDto
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class TaskUseCase : KoinComponent {

    val taskRepository by inject<TaskRepository>()

    fun findAll(): List<TaskUseCaseDto> {
        return taskRepository.findAll()
            .map { TaskUseCaseDto.domainToDto(it) }
    }

    fun findById(taskId: String): TaskUseCaseDto {
        return taskRepository.findById(TaskId.valueOf(taskId))
            ?.let { TaskUseCaseDto.domainToDto(it) }
            ?: throw IllegalArgumentException("The task is not found.")
    }

    fun create(taskUseCaseDto: TaskUseCaseDto) {
        taskUseCaseDto.createDomain()
            .let { taskRepository.save(it) }
    }

    fun remove(taskId: String) {
        val taskId = TaskId.valueOf(taskId)

        taskRepository.findById(taskId)
            ?.let { taskRepository.remove(taskId) }
            ?: throw IllegalArgumentException("The task is not found.")
    }

    fun update(taskUseCaseDto: TaskUseCaseDto) {
        val taskId = taskUseCaseDto.id?.let { TaskId.valueOf(it) }

        val currentTask = taskId
            ?.let { taskRepository.findById(it) }
            ?: throw IllegalArgumentException("The task is not found.")

        val changedTaskName = taskUseCaseDto.name
            ?.let { TaskName.valueOf(it) }
            ?: currentTask.taskName

        val changedTaskStatus = taskUseCaseDto.status
            ?.let { TaskStatus.valueOf(it) }
            ?: currentTask.taskStatus

        val changedDueDate = taskUseCaseDto.dueDate
            ?.let { DueDate.valueOf(it) }
            ?: currentTask.dueDate

        val changedTask = currentTask
            .changeName(changedTaskName)
            .changeStatus(changedTaskStatus)
            .changeDueDate(changedDueDate)

        taskRepository.update(changedTask)
    }
}
