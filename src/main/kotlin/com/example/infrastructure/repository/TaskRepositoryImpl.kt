package com.example.infrastructure.repository

import com.example.domain.model.task.entity.Task
import com.example.domain.model.task.value_object.TaskId
import com.example.domain.repository.TaskRepository
import com.example.infrastructure.repository.dto.TaskRepositoryDto
import org.jdbi.v3.sqlobject.kotlin.onDemand
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class TaskRepositoryImpl() : TaskRepository, KoinComponent {

    val databaseFactory by inject<DatabaseFactory>()

    val jdbi = databaseFactory.connect()

    val dao = jdbi.onDemand<TaskJdbiRepository>()

    override fun findById(taskId: TaskId): Task? {
        return dao.findById(taskId.value())
            ?.recordToDomain()
    }

    override fun findAll(): List<Task> {
        return dao.findAll()
            .map { it.recordToDomain() }
    }

    override fun save(task: Task) {
        val taskRepositoryDto = TaskRepositoryDto.domainToRecord(task)
        return dao.insert(taskRepositoryDto)
    }

    override fun remove(taskId: TaskId) {
        return dao.delete(taskId.value())
    }

    override fun update(task: Task) {
        val taskRepositoryDto = TaskRepositoryDto.domainToRecord(task)
        return dao.update(taskRepositoryDto)
    }
}
