package com.example.controller.request

import java.time.LocalDate

data class UpdateTaskRequest(
    val name: String? = null,
    val status: String? = null,
    val dueDate: LocalDate? = null
)
