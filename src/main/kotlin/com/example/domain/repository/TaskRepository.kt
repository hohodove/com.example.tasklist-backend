package com.example.domain.repository

import com.example.domain.model.task.entity.Task
import com.example.domain.model.task.value_object.TaskId

/**
 * タスクエンティティをもとにDB操作を行うインターフェース。
 */
interface TaskRepository {

    fun findById(taskId: TaskId): Task?

    fun findAll(): List<Task>

    fun save(task: Task)

    fun remove(taskId: TaskId)
    
    fun update(task: Task)
}
