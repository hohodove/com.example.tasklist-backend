package com.example.domain.model.task

import com.example.domain.share.ValueObject
import java.util.UUID

/**
 * タスクを一意に識別可能なIDを表現。
 *
 * IDはUUIDv4（64桁）。
 */
class TaskId private constructor(private val id: String) : ValueObject<String>(id) {

    companion object {

        // UUID形式を定義。
        private val pattern = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}".toRegex()

        /**
         * [UUID]を用いてタスクのIDを生成する。
         *
         * @return 自動生成された値を持つタスクID
         */
        fun generate(): TaskId = TaskId(UUID.randomUUID().toString())

        /**
         * [id]に指定された値をタスクのIDに変換する。
         *
         * 値には、UUIDv4形式の文字列を指定可能。
         * この条件に違反した場合は例外となる。
         *
         * @throws TaskInvalidRequestException 条件に違反した値を指定した場合
         * @return 指定された値を持つタスクID
         */
        fun valueOf(id: String): TaskId = id
            .takeIf { pattern.matches(it) }
            ?.let { TaskId(id) }
            ?: throw TaskInvalidRequestException(
                "Task id must be UUIDv4 format."
            )
    }

}
