package com.example.domain.repository

import com.example.domain.model.task.Task
import com.example.domain.model.task.TaskId
import com.example.infrastructure.framework.repository.mapping.TasksTableRecord

/**
 * タスクを操作するためのリポジトリを表現する。
 */
interface TaskRepository {

    fun findById(taskId: TaskId): TasksTableRecord

    fun findAll(): List<TasksTableRecord>

    fun save(task: Task)

    fun remove(taskId: TaskId)
}
