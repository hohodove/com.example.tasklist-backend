package com.example.infrastructure.repository

import org.jdbi.v3.core.Jdbi
import org.koin.core.component.KoinComponent

class PostgresDatabaseFactory(val dataSource: DataSource) : DatabaseFactory, KoinComponent {
    override fun connect(): Jdbi {

        return Jdbi.create(dataSource.url, dataSource.username, dataSource.password)
            .installPlugins()
    }
}
