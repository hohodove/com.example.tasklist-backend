package com.example.test_util

import org.jdbi.v3.sqlobject.SqlObject
import org.jdbi.v3.sqlobject.statement.SqlUpdate

interface InitDatabase : SqlObject {
    @SqlUpdate("""
        CREATE TABLE IF NOT EXISTS 
        tasks
        (
            id VARCHAR(36) PRIMARY KEY,
            name VARCHAR(100),
            status VARCHAR(50),
            duedate DATE
        )
    """)
    fun createTasksTable()
}
