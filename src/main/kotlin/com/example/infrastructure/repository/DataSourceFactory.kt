package com.example.infrastructure.repository

interface DataSourceFactory {
    fun create(): DataSource
}
