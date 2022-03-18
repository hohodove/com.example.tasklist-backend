package com.example.infrastructure.framework.repository

import com.example.domain.model.task.Task
import com.example.domain.model.task.TaskId
import com.example.domain.repository.TaskRepository
import com.example.infrastructure.framework.repository.mapping.TasksTableRecord
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.sqlobject.kotlin.onDemand

class TaskRepositoryImpl : TaskRepository {

    val jdbi: Jdbi = Jdbi.create("jdbc:postgresql://localhost:5432/test", "admin", "password")
        .installPlugins()
    val dao = jdbi.onDemand<TaskJdbiRepository>()

    override fun findById(taskId: TaskId): TasksTableRecord {
        return dao.findById(taskId)
    }

    override fun findAll(): List<TasksTableRecord> {
        return dao.findAll()
    }

    override fun save(task: Task) {
        return dao.insert(task)
    }

    override fun remove(taskId: TaskId) {
        return dao.delete(taskId)
    }
}
