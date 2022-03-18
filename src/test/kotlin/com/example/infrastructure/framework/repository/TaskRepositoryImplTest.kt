package com.example.infrastructure.framework.repository

import com.example.domain.model.task.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.time.LocalDate

internal class TaskRepositoryImplTest {

    val taskRepositoryImpl = TaskRepositoryImpl()

    @Test
    fun `タスクの保存、取得、削除ができる`() {
        val task1 = Task.reconstract(
            TaskId.valueOf("1234abcd-56ef-78ab-90cd-123456efabcd"),
            TaskName.valueOf("タスク１"),
            TaskStatus.NOT_COMPLETED,
            DueDate.reconstract(LocalDate.of(2001, 1, 1))
        )

        val task2 = Task.reconstract(
            TaskId.valueOf("2345bcde-67fa-89bc-01de-234567fabcde"),
            TaskName.valueOf("タスク２"),
            TaskStatus.NOT_COMPLETED,
            DueDate.reconstract(LocalDate.of(2002, 2, 2))
        )

        taskRepositoryImpl.save(task1)
        taskRepositoryImpl.save(task2)

        assertEquals(2, taskRepositoryImpl.findAll().size)

        val selectedTask = taskRepositoryImpl.findById(task1.taskId)
            .recordToDomain()

        assertEquals("タスク１", selectedTask.taskName.value())

        taskRepositoryImpl.remove(task1.taskId)
        taskRepositoryImpl.remove(task2.taskId)

        val tasks = taskRepositoryImpl.findAll()
        assertEquals(0, tasks.size)
    }
}
