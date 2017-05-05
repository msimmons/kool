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
 * for arithmetic operations
 */
@JsonSerialize(converter = Numeric::class)
class Numeric(number: Number?) : Comparable<Numeric>, Converter<Numeric, BigDecimal?> {

    override fun getInputType(typeFactory: TypeFactory?): JavaType {
        return SimpleType.constructUnsafe(Numeric::class.java)
    }

    override fun getOutputType(typeFactory: TypeFactory?): JavaType {
        return SimpleType.constructUnsafe(BigDecimal::class.java)
    }

    override fun convert(value: Numeric?): BigDecimal? {
        return when (value) {
            null -> null
            else -> when(value.value.isPresent) {
                true -> value.value.get()
                else -> null
            }
        }
    }

    constructor() : this(null)

    private var value = Optional.empty<BigDecimal>()

    init {
        when (number) {
            is BigDecimal -> value = Optional.of(number)
            null -> null
            else -> value = Optional.of(BigDecimal.valueOf(number.toDouble()))
        }
    }

    operator fun plus(x: Numeric) : Numeric {
        return when(value.isPresent && x.value.isPresent) {
            true -> Numeric(value.get() + x.value.get())
            else -> Numeric()
        }
    }

    operator fun plus(x: Number) : Numeric {
        return plus(Numeric(x))
    }

    operator fun minus(x: Numeric) : Numeric {
        return when(value.isPresent && x.value.isPresent) {
            true -> Numeric(value.get() - x.value.get())
            else -> Numeric()
        }
    }

    operator fun minus(x: Number) : Numeric {
        return minus(Numeric(x))
    }

    operator fun times(x: Numeric) : Numeric {
        return when(value.isPresent && x.value.isPresent) {
            true -> Numeric(value.get() * x.value.get())
            else -> Numeric()
        }
    }

    operator fun times(x: Number) : Numeric {
        return times(Numeric(x))
    }

    operator fun div(x: Numeric) : Numeric {
        return when(value.isPresent && x.value.isPresent) {
            true -> Numeric(value.get() / x.value.get())
            else -> Numeric()
        }
    }

    operator fun div(x: Number) : Numeric {
        return div(Numeric(x))
    }

    override fun equals(x: Any?) : Boolean {
        return when(x) {
            null -> return !value.isPresent
            is Numeric -> equals(x)
            is Number -> equals(x)
            else -> false
        }
    }

    private fun equals(x: Number) : Boolean {
        return equals(Numeric(x))
    }

    private fun equals(x: Numeric) : Boolean {
        return when(value.isPresent && x.value.isPresent) {
            true -> value.get().compareTo(x.value.get()) == 0
            else -> false
        }
    }

    override operator fun compareTo(x: Numeric) : Int {
        return when(value.isPresent && x.value.isPresent) {
            true -> value.get().compareTo(x.value.get())
            else -> 0
        }
    }

    operator fun compareTo(x: Number) : Int {
        return compareTo(Numeric(x))
    }

    override fun toString() : String {
        return if ( value.isPresent ) value.get().toPlainString() else ""
    }
}