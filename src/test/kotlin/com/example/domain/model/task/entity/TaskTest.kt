package com.example.domain.model.task.entity

import com.example.domain.model.task.value_object.DueDate
import com.example.domain.model.task.value_object.TaskId
import com.example.domain.model.task.value_object.TaskName
import com.example.domain.model.task.value_object.TaskStatus
import com.example.test_util.TaskTestFactory
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.time.LocalDate

internal class TaskTest {

    @Test
    fun `タスク期限を省略した場合、タスク期限が当日のタスクが新規作成できること`() {

        val taskName = "タスク１"
        val task = Task.create(
            TaskName.valueOf(taskName)
        )

        assertEquals(taskName, task.taskName.value())
        assertEquals(LocalDate.now(), task.dueDate.value())
    }

    @Test
    fun `タスク期限を指定して、タスクが新規作成できること`() {
        val taskName = "タスク１"
        val dueDate = LocalDate.now().plusDays(1)

        val task = Task.create(
            TaskName.valueOf(taskName),
            DueDate.valueOf(dueDate)
        )

        assertEquals(taskName, task.taskName.value())
        assertEquals(dueDate, task.dueDate.value())
    }


    @Test
    fun `引数の値を全て指定してタスクの生成ができること`() {
        val taskId = TaskId.valueOf("1234abcd-56ef-78ab-90cd-123456efabcd")
        val taskName = TaskName.valueOf("タスク１")
        val taskStatus = TaskStatus.DONE
        val dueDate = DueDate.reconstruct(LocalDate.of(2001,1,1))

        val task = Task.reconstruct(
            taskId,
            taskName,
            taskStatus,
            dueDate
        )

        assertEquals(taskId, task.taskId)
        assertEquals(taskName, task.taskName)
        assertEquals(taskStatus, task.taskStatus)
        assertEquals(dueDate, task.dueDate)
    }

    @Test
    fun `生成したタスクのタスク名が変更できること`() {
        val task = TaskTestFactory.create()

        val changedTaskName = TaskName.valueOf("変更後タスク１")
        val changedTask = task.changeName(changedTaskName)

        assertEquals(changedTaskName, changedTask.taskName)
    }

    @Test
    fun `生成したタスクの状態を完了にできること`() {
        val task = TaskTestFactory.create()

        val doneTask = task.done()

        assertEquals(TaskStatus.DONE, doneTask.taskStatus)
    }

    @Test
    fun `完了状態であるタスクの状態を未完了状態に変更できること`() {
        val task = TaskTestFactory.create()
        val doneTask = task.done()

        val undoneTask = task.undone()

        assertEquals(TaskStatus.NOT_COMPLETED, undoneTask.taskStatus)

    }

    @Test
    fun `生成したタスクの期限を変更できること`() {
        val task = TaskTestFactory.create()

        val changedDueDate = DueDate.valueOf(task.dueDate.value().plusDays(1))
        val changedTask = task.changeDueDate(changedDueDate)

        assertEquals(changedDueDate, changedTask.dueDate)
    }
}
