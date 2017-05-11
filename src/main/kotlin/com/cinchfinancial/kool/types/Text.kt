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
@JsonSerialize(converter = Text.JsonConverter::class)
class Text(text: String?) : CharSequence, BaseType, Comparable<Text> {

    constructor() : this(null)

    override val length: Int = text?.length ?: 0
    private val textValue = Optional.ofNullable(text)

    override fun compareTo(other: Text): Int {
        return when (textValue.isPresent && other.textValue.isPresent) {
            true -> textValue.get().compareTo(other.textValue.get())
            else -> 0
        }
    }

    operator fun compareTo(other: String) : Int {
        return when (textValue.isPresent) {
            true -> textValue.get().compareTo(other)
            else -> 0
        }
    }

    override fun isNull(): Boolean {
        return !textValue.isPresent
    }

    override fun get(index: Int): Char {
        return if ( textValue.isPresent ) textValue.get().get(index) else ' '
    }

    override fun subSequence(startIndex: Int, endIndex: Int): CharSequence {
        return if ( textValue.isPresent ) textValue.get().subSequence(startIndex, endIndex) else ""
    }

    override fun equals(other: Any?): Boolean {
        return when(other) {
            null -> !textValue.isPresent
            is Text -> equals(other)
            is String -> equals(other)
            else -> false
        }
    }

    private fun equals(other: Text) : Boolean {
        return when (other.textValue.isPresent) {
            true -> equals(other.textValue.get())
            else -> !textValue.isPresent
        }
    }

    private fun equals(other: String) : Boolean {
        return when (textValue.isPresent) {
            true -> textValue.get().equals(other)
            else -> false
        }
    }

    companion object JsonConverter : Converter<Text, String?> {

        override fun convert(value: Text?): String? {
            return when (value) {
                null -> null
                else -> value.textValue.orElse(null)
            }
        }

        override fun getInputType(typeFactory: TypeFactory?): JavaType {
            return SimpleType.constructUnsafe(Text::class.java)
        }

        override fun getOutputType(typeFactory: TypeFactory?): JavaType {
            return SimpleType.constructUnsafe(String::class.java)
        }

    }
}