package com.example.infrastructure.repository

import org.jdbi.v3.core.Jdbi

interface DatabaseFactory {
    fun connect(): Jdbi
}
