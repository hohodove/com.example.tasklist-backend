package com.example.test_util

import com.example.infrastructure.framework.koin_modules.taskRepositoryModule
import org.koin.core.logger.Level
import org.koin.test.junit5.KoinTestExtension

object KoinTestConfig {
    fun set() = KoinTestExtension.create {
        // KoinのIssue #1188の通り、Ktor 1.6.0以降でKoinにてNoSuchMethodError例外が発生するため、
        // workaroundとして、ロガーのログレベルにERRORを設定。
        printLogger(level = Level.ERROR)
        modules(taskRepositoryModule)
    }
}
