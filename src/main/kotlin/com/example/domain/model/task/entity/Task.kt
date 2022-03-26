package com.example.domain.model.task.entity

import com.example.domain.model.task.value_object.DueDate
import com.example.domain.model.task.value_object.TaskId
import com.example.domain.model.task.value_object.TaskName
import com.example.domain.model.task.value_object.TaskStatus

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
         * タスク期限日を省略した場合、当日日付を設定。
         *
         * @return 作成されたタスク
         */
        fun create(
            taskName: TaskName,
            dueDate: DueDate = DueDate.createDefault()
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

    /**
     * タスク名を変更する。
     *
     * @return タスク名が変更されたタスク
     */
    fun changeName(changedName: TaskName): Task = Task(
        taskId,
        changedName,
        taskStatus,
        dueDate
    )

    /**
     * タスク状態を完了にする。
     *
     * @return タスク状態が完了となったタスク
     */
    fun done(): Task = Task(
            taskId,
            taskName,
            TaskStatus.DONE,
            dueDate
        )

    /**
     * タスクを未完了状態にする。
     *
     * @return タスク状態が未完了となったタスク
     */
    fun undone(): Task = Task(
            taskId,
            taskName,
            TaskStatus.NOT_COMPLETED,
            dueDate
        )

    fun changeStatus(changedStatus: TaskStatus): Task = Task(
        taskId,
        taskName,
        changedStatus,
        dueDate
    )

    /**
     * タスク期限を変更する。
     *
     * @return タスク期限が変更されたタスク
     */
    fun changeDueDate(changedDueDate: DueDate): Task = Task(
        taskId,
        taskName,
        taskStatus,
        changedDueDate
    )

}
