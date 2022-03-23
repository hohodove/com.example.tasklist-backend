package com.example.infrastructure.repository.dto

import com.example.domain.model.task.value_object.DueDate
import com.example.domain.model.task.entity.Task
import com.example.domain.model.task.value_object.TaskId
import com.example.domain.model.task.value_object.TaskName
import com.example.domain.model.task.value_object.TaskStatus
import org.jdbi.v3.core.mapper.reflect.ColumnName
import java.time.LocalDate

/**
 * タスクTBLマッピング用のオブジェクト。
 */
data class TaskRepositoryDto(
    @ColumnName("id") val taskId: String,
    @ColumnName("name") val taskName: String,
    @ColumnName("status") val taskStatus: String,
    @ColumnName("duedate") val dueDate: LocalDate
) {

    /**
     * タスクTBLマッピング用のオブジェクトからエンティティを生成。
     *
     * @return 生成されたタスク。
     */
    fun recordToDomain(): Task =
        Task.reconstract(
            TaskId.valueOf(taskId),
            TaskName.valueOf(taskName),
            TaskStatus.valueOf(taskStatus),
            DueDate.reconstruct(dueDate)
        )

    companion object {
        /**
         * エンティティからタスクTBLマッピング用のオブジェクトを生成。
         *
         * @return 生成されたタスクTBLマッピング用オブジェクト。
         */
        fun domainToRecord(task: Task): TaskRepositoryDto =
            TaskRepositoryDto(
                task.taskId.value(),
                task.taskName.value(),
                task.taskStatus.toString(),
                task.dueDate.value()
            )

    }
}
