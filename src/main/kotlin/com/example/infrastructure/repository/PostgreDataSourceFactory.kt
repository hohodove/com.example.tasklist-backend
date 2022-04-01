package com.example.infrastructure.repository

class PostgreDataSourceFactory : DataSourceFactory {
    override fun create(): DataSource = DataSource(
        "jdbc:postgresql://localhost:5432/test",
        "admin",
        "password"
    )
}
