package com.example.domain.model.task

import com.example.domain.share.ValueObject

/**
 * タスク名を表現する。
 *
 * タスク名は100文字を上限とする。
 */
class TaskName private constructor(private val value: String) : ValueObject<String>(value) {

    companion object {

        private val LENGTH_RANGE = (1..100)

        /**
         * [value]に設定した値からタスク名を生成する。
         *
         * 値は1から100桁までの文字列を指定可能。
         *
         * @throws TaskInvalidRequestException 条件に違反した文字列を指定した場合
         * @return 指定された値を持つタスク名
         */
        fun valueOf(value: String): TaskName = value
            .takeIf { LENGTH_RANGE.contains(it.length) }
            ?.let { TaskName(it) }
            ?: throw TaskInvalidRequestException("TaskName must be 100 characters or less.")
    }
}
