package com.example.controller.request

import java.time.LocalDate

data class CreateTaskRequest(
    val name: String,
    val duedate: LocalDate? = null
)
