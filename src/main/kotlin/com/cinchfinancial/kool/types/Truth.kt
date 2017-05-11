package com.cinchfinancial.kool.types

import com.fasterxml.jackson.databind.JavaType
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.type.SimpleType
import com.fasterxml.jackson.databind.type.TypeFactory
import com.fasterxml.jackson.databind.util.Converter
import java.util.*

/**
 * Null-safe representation of a textual type
 */
@JsonSerialize(converter = Truth.JsonConverter::class)
class Truth(truth: Boolean?) : BaseType {

    constructor() : this(null)

    private val truthValue = Optional.ofNullable(truth)

    override fun isNull(): Boolean {
        return !truthValue.isPresent
    }

    override fun equals(other: Any?): Boolean {
        return when(other) {
            null -> !truthValue.isPresent
            is Truth -> equals(other)
            is Boolean -> equals(other)
            else -> false
        }
    }

    private fun equals(other: Truth) : Boolean {
        return when (other.truthValue.isPresent) {
            true -> equals(other.truthValue.get())
            else -> !truthValue.isPresent
        }
    }

    private fun equals(other: Boolean) : Boolean {
        return when (truthValue.isPresent) {
            true -> truthValue.get().equals(other)
            else -> false
        }
    }

    companion object JsonConverter : Converter<Truth, Boolean?> {

        override fun convert(value: Truth?): Boolean? {
            return when (value) {
                null -> null
                else -> value.truthValue.orElse(null)
            }
        }

        override fun getInputType(typeFactory: TypeFactory?): JavaType {
            return SimpleType.constructUnsafe(Truth::class.java)
        }

        override fun getOutputType(typeFactory: TypeFactory?): JavaType {
            return SimpleType.constructUnsafe(Boolean::class.java)
        }

    }
}