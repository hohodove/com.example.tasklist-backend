package com.example.infrastructure.framework.repository.mapping

import com.example.domain.model.task.*
import org.jdbi.v3.core.mapper.reflect.ColumnName
import java.time.LocalDate

/**
 * タスクTBLマッピング用のオブジェクト。
 */
data class TasksTableRecord(
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
            DueDate.reconstract(dueDate)
        )
}
