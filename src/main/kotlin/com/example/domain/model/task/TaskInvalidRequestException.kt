package com.example.domain.model.task

/**
 * 無効なリクエストを受け、タスクのドメインモデルへの変換に失敗した場合に発生する例外。
 */
class TaskInvalidRequestException(
    override val message: String,
    cause: Throwable? = null
) : RuntimeException(message, cause) {

    val type: String = "invalid_request_error"
}
