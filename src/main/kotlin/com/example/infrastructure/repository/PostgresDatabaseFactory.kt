package com.example.infrastructure.repository

import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.KotlinPlugin
import org.jdbi.v3.postgres.PostgresPlugin
import org.jdbi.v3.sqlobject.kotlin.KotlinSqlObjectPlugin
import org.koin.core.component.KoinComponent

class PostgresDatabaseFactory(val dataSource: DataSource) : DatabaseFactory, KoinComponent {
    override fun connect(): Jdbi {

        return Jdbi.create(dataSource.url, dataSource.username, dataSource.password)
            .installPlugin(KotlinPlugin())
            .installPlugin(KotlinSqlObjectPlugin())
            .installPlugin(PostgresPlugin())
    }
}
