package com.example.domain.model.task

import java.time.LocalDate

class Task private constructor(
    private val taskId: TaskId,
    private val taskName: TaskName,
    private val taskStatus: TaskStatus,
    private val dueDate: DueDate
) {

    /**
     * タスク状態を完了にする。
     *
     * @return タスク状態が完了となったタスク
     */
    fun done(): Task {
        return Task(
            taskId,
            taskName,
            TaskStatus.DONE,
            dueDate
        )
    }

    companion object {

        /**
         * タスクを作成する。
         *
         * タスクID、タスク名、タスク状態、タスク期限日が必要。
         *
         * タスク状態を省略した場合、未完了状態で作成する。
         * タスク期限日を省略した場合、当日日付が設定される。
         *
         * @return 作成されたタスク
         */
        fun create(
            taskId: TaskId,
            taskName: TaskName,
            taskStatus: TaskStatus = TaskStatus.NOT_COMPLETED,
            dueDate: DueDate = DueDate.valueOf(LocalDate.now())
        ): Task {
            return Task(
                taskId,
                taskName,
                taskStatus,
                dueDate
            )
        }
    }
}
