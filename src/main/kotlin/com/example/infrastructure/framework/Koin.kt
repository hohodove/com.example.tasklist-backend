package com.example.infrastructure.framework

import com.example.infrastructure.framework.koin_modules.taskRepositoryModule
import io.ktor.application.*
import org.koin.core.logger.Level
import org.koin.ktor.ext.Koin
import org.koin.logger.slf4jLogger

fun Application.configureKoin() {
    install(Koin) {
        //KoinのIssue #1188の通り、Ktor 1.6.0以降でKoinにてNoSuchMethodError例外が発生するため、
        // workaroundとして、ロガーのログレベルをERRORで設定している。
        slf4jLogger(level = Level.ERROR)  //
        modules(taskRepositoryModule)
    }
}
