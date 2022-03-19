package com.example.infrastructure.repository

import com.example.domain.model.task.Task
import com.example.domain.model.task.TaskId
import com.example.domain.repository.TaskRepository
import com.example.infrastructure.repository.mapping.TasksTableRecord
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.sqlobject.kotlin.onDemand

class TaskRepositoryImpl : TaskRepository {

    val jdbi: Jdbi = Jdbi.create("jdbc:postgresql://localhost:5432/test", "admin", "password")
        .installPlugins()
    val dao = jdbi.onDemand<TaskJdbiRepository>()

    override fun findById(taskId: TaskId): Task {
        return dao.findById(taskId.value())
            .recordToDomain()
    }

    override fun findAll(): List<Task> {
        return dao.findAll()
            .map { it.recordToDomain() }
    }

    override fun save(task: Task) {
        val tasksTableRecord = TasksTableRecord.domainToRecord(task)
        return dao.insert(tasksTableRecord)
    }

    override fun remove(taskId: TaskId) {
        return dao.delete(taskId.value())
    }
}
