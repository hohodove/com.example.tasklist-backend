package com.example.domain.model.task.value_object

import com.example.domain.model.task.exception.TaskInvalidRequestException
import com.example.domain.share.ValueObject
import java.time.LocalDate

/**
 * タスク期限日を表現する。
 *
 * タスク期限日はタスク作成当日以降の日付である必要がある。
 * 日付を指定して作成しない場合、タスク期限日は当日を設定する。
 */
class DueDate private constructor(val value: LocalDate) : ValueObject<LocalDate>(value) {

    companion object {

        /**
         * [value]に設定した値からタスク期限日を作成する。
         *
         * 値はタスク作成日以降の日付が設定可能。
         * 値がnullの場合、当時の日付を設定。
         *
         * @throws TaskInvalidRequestException 条件に違反した日付を設定した場合
         * @return 指定された値を持つタスク期限日
         */
        fun valueOf(value: LocalDate?): DueDate {
            if (value == null) {
                return DueDate(LocalDate.now())
            } else {
                return value
                    .takeIf { it.isAfter(LocalDate.now().minusDays(1)) }
                    ?.let { DueDate(it) }
                    ?: throw TaskInvalidRequestException("DueDate($value) must be after today.")
            }
        }

        fun reconstruct(value: LocalDate): DueDate = DueDate(value)
    }
}
