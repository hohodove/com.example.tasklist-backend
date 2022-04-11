package com.example.test_util

import com.example.infrastructure.repository.DataSource
import com.example.infrastructure.repository.DatabaseFactory
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.h2.H2DatabasePlugin
import org.jdbi.v3.core.kotlin.KotlinPlugin
import org.jdbi.v3.sqlobject.kotlin.KotlinSqlObjectPlugin
import org.jdbi.v3.sqlobject.kotlin.onDemand

class TestDatabaseFactory(val dataSource: DataSource) : DatabaseFactory {
    override fun connect(): Jdbi {

        val jdbi = Jdbi.create(dataSource.url)
            // .installPlugins()の場合、PostgresPluginがオートでインストールされ、結果として、H2への接続にて以下のエラーが発生するため、
            // .installPlugin()にて手動で設定を行っている。
            // org.h2.jdbc.JdbcSQLDataException: パラメータ "iface" に対する値 "interface org.postgresql.PGConnection" が不正です
            .installPlugin(KotlinPlugin())
            .installPlugin(KotlinSqlObjectPlugin())
            .installPlugin(H2DatabasePlugin())

        val dao = jdbi.onDemand<InitDatabase>()
        dao.createTasksTable()

        return jdbi
    }

}
