package com.example.domain.repository

import com.example.domain.model.task.Task
import com.example.domain.model.task.TaskId

/**
 * タスクエンティティをもとにDB操作を行うインターフェース。
 */
interface TaskRepository {

    fun findById(taskId: TaskId): Task

    fun findAll(): List<Task>

    fun save(task: Task)

    fun remove(taskId: TaskId)
}