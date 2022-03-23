package com.example.controller.response

import java.time.LocalDate

data class FindAllTasksResponse(
    val id: String,
    val name: String,
    val status: String,
    val dueDate: String
)
