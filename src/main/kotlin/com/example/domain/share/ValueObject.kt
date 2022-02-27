package com.example.domain.share

import java.io.Serializable

abstract class ValueObject<V>(private val value: V) : Serializable {
    fun value(): V = value
}
