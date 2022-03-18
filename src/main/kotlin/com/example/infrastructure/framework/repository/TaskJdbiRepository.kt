package com.example.infrastructure.framework.repository

import com.example.domain.model.task.Task
import com.example.domain.model.task.TaskId
import com.example.infrastructure.framework.repository.mapping.TasksTableRecord
import org.jdbi.v3.sqlobject.SqlObject
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate

interface TaskJdbiRepository : SqlObject {

    @SqlQuery("""
        select *
        from tasks
        where id = :taskId.value
    """)
    fun findById(taskId: TaskId): TasksTableRecord

    @SqlQuery("""
        select * from tasks
    """)
    fun findAll(): List<TasksTableRecord>

    @SqlUpdate("""
        insert
        into tasks 
        values (:task.taskId.value,
                :task.taskName.value,
                :task.taskStatus,
                :task.dueDate.value
                )
    """)
    fun insert(task: Task)

    @SqlUpdate("""
        delete
        from tasks
        where id = :taskId.value
    """)
    fun delete(taskId: TaskId)
}
