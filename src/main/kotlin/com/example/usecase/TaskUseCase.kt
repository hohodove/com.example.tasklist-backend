package com.example.usecase

import com.example.domain.model.task.value_object.TaskId
import com.example.infrastructure.repository.TaskRepositoryImpl
import com.example.usecase.dto.TaskUseCaseDto

class TaskUseCase {

    private val taskRepository = TaskRepositoryImpl()

    fun findAll(): List<TaskUseCaseDto> {
        return taskRepository.findAll()
            .map { TaskUseCaseDto.domainToDto(it) }
    }

    fun findById(taskId: String): TaskUseCaseDto? {
        return taskRepository.findById(TaskId.valueOf(taskId))
            ?.let { TaskUseCaseDto.domainToDto(it) }
    }

    fun create(taskUseCaseDto: TaskUseCaseDto) {
        taskUseCaseDto.createDomain()
            .let { taskRepository.save(it) }
    }

    fun remove(taskId: String) {
        taskRepository.remove(TaskId.valueOf(taskId))
    }
}
