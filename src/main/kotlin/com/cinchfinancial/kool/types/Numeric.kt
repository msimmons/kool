package com.cinchfinancial.kool.types

import com.fasterxml.jackson.databind.JavaType
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.type.SimpleType
import com.fasterxml.jackson.databind.type.TypeFactory
import com.fasterxml.jackson.databind.util.Converter
import java.math.BigDecimal
import java.util.*

/**
 * Null safe and flexible numeric representation.  Turns everything into [BigDecimal]
 * for arithmetic operations, returns decimal or integral value to json as appropriate
 */
@JsonSerialize(converter = Numeric.JsonConverter::class)
class Numeric(number: Number?) : Comparable<Numeric>, BaseType {

    constructor() : this(null)

    private val numericValue : Optional<BigDecimal>
    val isDecimal : Boolean

    init {
        numericValue = when (number) {
            null -> Optional.empty()
            is BigDecimal -> Optional.of(number)
            else -> Optional.of(BigDecimal.valueOf(number.toDouble()))
        }
        isDecimal = when (number) {
            is BigDecimal -> true
            is Double -> true
            is Float -> true
            else -> false
        }
    }

    override fun isNull(): Boolean {
        return !numericValue.isPresent
    }

    operator fun plus(x: Numeric) : Numeric {
        return when(numericValue.isPresent && x.numericValue.isPresent) {
            true -> Numeric(numericValue.get() + x.numericValue.get())
            else -> Numeric()
        }
    }

    operator fun plus(x: Number) : Numeric {
        return plus(Numeric(x))
    }

    operator fun minus(x: Numeric) : Numeric {
        return when(numericValue.isPresent && x.numericValue.isPresent) {
            true -> Numeric(numericValue.get() - x.numericValue.get())
            else -> Numeric()
        }
    }

    operator fun minus(x: Number) : Numeric {
        return minus(Numeric(x))
    }

    operator fun times(x: Numeric) : Numeric {
        return when(numericValue.isPresent && x.numericValue.isPresent) {
            true -> Numeric(numericValue.get() * x.numericValue.get())
            else -> Numeric()
        }
    }

    operator fun times(x: Number) : Numeric {
        return times(Numeric(x))
    }

    operator fun div(x: Numeric) : Numeric {
        return when(numericValue.isPresent && x.numericValue.isPresent) {
            true -> Numeric(numericValue.get() / x.numericValue.get())
            else -> Numeric()
        }
    }

    operator fun div(x: Number) : Numeric {
        return div(Numeric(x))
    }

    override fun equals(x: Any?) : Boolean {
        return when(x) {
            null -> return !numericValue.isPresent
            is Numeric -> equals(x)
            is Number -> equals(x)
            else -> false
        }
    }

    private fun equals(x: Number) : Boolean {
        return equals(Numeric(x))
    }

    private fun equals(x: Numeric) : Boolean {
        return when(numericValue.isPresent && x.numericValue.isPresent) {
            true -> numericValue.get().compareTo(x.numericValue.get()) == 0
            else -> false
        }
    }

    override operator fun compareTo(x: Numeric) : Int {
        return when(numericValue.isPresent && x.numericValue.isPresent) {
            true -> numericValue.get().compareTo(x.numericValue.get())
            else -> 0
        }
    }

    operator fun compareTo(x: Number) : Int {
        return compareTo(Numeric(x))
    }

    override fun toString() : String {
        return if ( numericValue.isPresent ) numericValue.get().toPlainString() else ""
    }

    companion object JsonConverter : Converter<Numeric, Number?> {
        override fun getOutputType(typeFactory: TypeFactory?): JavaType {
            return SimpleType.constructUnsafe(Number::class.java)
        }

        override fun convert(value: Numeric?): Number? {
            return when (value) {
                null -> null
                else -> when(value.numericValue.isPresent) {
                    true -> when (value.isDecimal) {
                        true -> value.numericValue.get()
                        else -> value.numericValue.get().toLong() as Number
                    }
                    else -> null
                }
            }
        }

        override fun getInputType(typeFactory: TypeFactory?): JavaType {
            return SimpleType.constructUnsafe(Numeric::class.java)
        }

    }
}