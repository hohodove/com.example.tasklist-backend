package com.example.test_util

import com.example.infrastructure.framework.module
import io.ktor.config.*
import io.ktor.server.testing.*

fun MapApplicationConfig.createTestConfig() {
    put("ktor.dataSource.url", "jdbc:postgresql://localhost:5432/test")
    put("ktor.dataSource.username", "admin")
    put("ktor.dataSource.password", "password")
}

fun withTestModule(block: TestApplicationEngine.() -> Unit) {
    withTestApplication({
        (environment.config as MapApplicationConfig).apply {
            createTestConfig()
        }
        module()
    }) {
        block
    }
}
