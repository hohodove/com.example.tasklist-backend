package com.example.domain.model.task

import com.example.domain.model.task.exception.TaskInvalidRequestException
import com.example.domain.model.task.value_object.TaskName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows

internal class TaskNameTest {

    @Test
    fun `値を指定してタスク名が生成できる`() {
        val taskName = "a".repeat(100)

        assertEquals(taskName, TaskName.valueOf(taskName).value())
    }

    @Test
    fun `100文字を超えたタスク名は生成できない`() {
        val taskName = "a".repeat(101)

        val error = assertThrows<TaskInvalidRequestException> {
            TaskName.valueOf(taskName)
        }
        assertEquals("TaskName must be 100 characters or less.", error.message)

    }
}
