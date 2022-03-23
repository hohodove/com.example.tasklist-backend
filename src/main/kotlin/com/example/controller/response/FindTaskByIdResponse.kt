package com.example.controller.response

data class FindTaskByIdResponse(
    val id: String,
    val name: String,
    val status: String,
    val dueDate: String
)
