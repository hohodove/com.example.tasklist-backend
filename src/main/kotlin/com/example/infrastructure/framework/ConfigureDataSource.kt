package com.example.infrastructure.framework

import com.example.infrastructure.repository.DataSource
import io.ktor.application.*
import org.koin.ktor.ext.inject

fun Application.configureDataSource() {

    val dataSource by inject<DataSource>()

    val dataSourceObject = environment.config.config("ktor.dataSource")
    dataSource.url =  dataSourceObject.property("url").getString()
    dataSource.username = dataSourceObject.property("username").getString()
    dataSource.password = dataSourceObject.property("password").getString()
}
