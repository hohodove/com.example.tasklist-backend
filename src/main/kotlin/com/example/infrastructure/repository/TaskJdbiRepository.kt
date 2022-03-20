package com.example.infrastructure.repository

import com.example.infrastructure.repository.dto.TaskRepositoryDto
import org.jdbi.v3.sqlobject.SqlObject
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate

interface TaskJdbiRepository : SqlObject {

    @SqlQuery("""
        select *
        from tasks
        where id = :taskId
    """)
    fun findById(taskId: String): TaskRepositoryDto?

    @SqlQuery("""
        select * from tasks
    """)
    fun findAll(): List<TaskRepositoryDto>

    @SqlUpdate("""
        insert
        into tasks 
        values (:taskRepositoryDto.taskId,
                :taskRepositoryDto.taskName,
                :taskRepositoryDto.taskStatus,
                :taskRepositoryDto.dueDate
                )
    """)
    fun insert(taskRepositoryDto: TaskRepositoryDto)

    @SqlUpdate("""
        delete
        from tasks
        where id = :taskId
    """)
    fun delete(taskId: String)
}
