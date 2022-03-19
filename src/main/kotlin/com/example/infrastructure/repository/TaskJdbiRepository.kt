package com.example.infrastructure.repository

import com.example.infrastructure.repository.mapping.TasksTableRecord
import org.jdbi.v3.sqlobject.SqlObject
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate

interface TaskJdbiRepository : SqlObject {

    @SqlQuery("""
        select *
        from tasks
        where id = :taskId
    """)
    fun findById(taskId: String): TasksTableRecord

    @SqlQuery("""
        select * from tasks
    """)
    fun findAll(): List<TasksTableRecord>

    @SqlUpdate("""
        insert
        into tasks 
        values (:tasksTableRecord.taskId,
                :tasksTableRecord.taskName,
                :tasksTableRecord.taskStatus,
                :tasksTableRecord.dueDate
                )
    """)
    fun insert(tasksTableRecord: TasksTableRecord)

    @SqlUpdate("""
        delete
        from tasks
        where id = :taskId
    """)
    fun delete(taskId: String)
}
