package com.example.test_util

import com.example.infrastructure.framework.module
import com.example.infrastructure.repository.DatabaseFactory
import io.ktor.config.*
import io.ktor.server.testing.*
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

fun MapApplicationConfig.createTestConfig() {
    put("ktor.dataSource.url", "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1")
    // username, passwordはH2の接続には不要だが、DataSourceクラスのusername, passwordはnullを非許容のため、ダミーで設定している。
    put("ktor.dataSource.username", "")
    put("ktor.dataSource.password", "")
}

fun withTestModule(block: TestApplicationEngine.() -> Unit) {
    withTestApplication({
        (environment.config as MapApplicationConfig).apply {
            createTestConfig()
        }
        module()
        // DatabaseFactoryのDI対象をテスト用に上書き
        loadKoinModules(module {
            single<DatabaseFactory> { TestDatabaseFactory(get()) }
        })
    }, block)
}
