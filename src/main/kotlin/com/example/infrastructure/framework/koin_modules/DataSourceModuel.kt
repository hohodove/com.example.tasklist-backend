package com.example.infrastructure.framework.koin_modules

import com.example.infrastructure.repository.DataSource
import com.example.infrastructure.repository.DatabaseFactory
import com.example.infrastructure.repository.PostgresDatabaseFactory
import org.koin.dsl.module
import org.koin.dsl.single

val dataSourceModule = module {
    single<DataSource>()
    single<DatabaseFactory> { PostgresDatabaseFactory(get()) }
}
