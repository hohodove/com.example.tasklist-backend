package com.example.domain.model.task.value_object

import com.example.domain.model.task.exception.TaskInvalidRequestException
import com.example.domain.model.task.value_object.DueDate
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate

internal class DueDateTest {

    @Test
    fun `値に当日を指定してタスク期限日が作成できる`() {
        val today = LocalDate.now()

        assertEquals(today, DueDate.valueOf(today).value())
    }
    
    @Test
    fun `タスク作成日前日以前を指定した場合、タスク期限日は作成できない`() {
        val yesterday = LocalDate.now().minusDays(1)

        val error = assertThrows<TaskInvalidRequestException> {
            DueDate.valueOf(yesterday)
        }
        assertEquals("DueDate($yesterday) must be after today.", error.message)
    }

}
