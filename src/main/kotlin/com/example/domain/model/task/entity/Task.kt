package com.example.domain.model.task.entity

import com.example.domain.model.task.value_object.DueDate
import com.example.domain.model.task.value_object.TaskId
import com.example.domain.model.task.value_object.TaskName
import com.example.domain.model.task.value_object.TaskStatus
import java.time.LocalDate

class Task private constructor(
    val taskId: TaskId,
    val taskName: TaskName,
    val taskStatus: TaskStatus,
    val dueDate: DueDate
) {

    companion object {

        /**
         * タスクを作成する。
         *
         * タスク名、タスク期限日が必要。
         *
         * タスク期限日を省略した場合、当日日付が設定される。
         *
         * @return 作成されたタスク
         */
        fun create(
            taskName: TaskName,
            dueDate: DueDate = DueDate.valueOf(LocalDate.now())
        ): Task =
            Task(
                TaskId.generate(),
                taskName,
                TaskStatus.NOT_COMPLETED,
                dueDate
            )

        /**
         * タスクを再構成する。
         *
         * タスクID、タスク名、タスク状態、タスク期限日が必要。
         *
         * @return 再構成されたタスク
         */
        fun reconstruct(
            taskId: TaskId,
            taskName: TaskName,
            taskStatus: TaskStatus,
            dueDate: DueDate
        ): Task =
            Task(taskId,
                taskName,
                taskStatus,
                dueDate
            )

    }
}
