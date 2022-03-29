package com.example.infrastructure.framework.koin_modules

import com.example.domain.repository.TaskRepository
import com.example.infrastructure.repository.TaskRepositoryImpl
import org.koin.dsl.module

val taskRepositoryModule = module {
    single<TaskRepository> { TaskRepositoryImpl() }
}
