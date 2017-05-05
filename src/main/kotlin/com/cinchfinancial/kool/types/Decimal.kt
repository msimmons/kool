package com.cinchfinancial.kool.types

import java.math.BigDecimal
import java.util.*

/**
 * Null safe and flexible decimal representation
 */
class Decimal(number: Number?) : Comparable<Decimal> {

    constructor() : this(null)

    private var value = Optional.empty<BigDecimal>()

    init {
        when (number) {
            is BigDecimal -> value = Optional.of(number)
            null -> null
            else -> value = Optional.of(BigDecimal.valueOf(number.toDouble()))
        }
    }

    operator fun plus(x: Decimal) : Decimal {
        return when(value.isPresent && x.value.isPresent) {
            true -> Decimal(value.get() + x.value.get())
            else -> Decimal()
        }
    }

    operator fun minus(x: Decimal) : Decimal {
        return when(value.isPresent && x.value.isPresent) {
            true -> Decimal(value.get() - x.value.get())
            else -> Decimal()
        }
    }

    operator fun times(x: Decimal) : Decimal {
        return when(value.isPresent && x.value.isPresent) {
            true -> Decimal(value.get() * x.value.get())
            else -> Decimal()
        }
    }

    operator fun div(x: Decimal) : Decimal {
        return when(value.isPresent && x.value.isPresent) {
            true -> Decimal(value.get() / x.value.get())
            else -> Decimal()
        }
    }

    operator fun div(x: Number) : Decimal {
        return when(value.isPresent) {
            true -> Decimal(value.get() / Decimal(x).value.get())
            else -> Decimal()
        }
    }

    override fun equals(x: Any?) : Boolean {
        return when(x) {
            null -> return !value.isPresent
            is Decimal -> equals(x)
            is Number -> equals(x)
            else -> false
        }
    }

    private fun equals(x: Number) : Boolean {
        return equals(Decimal(x))
    }

    private fun equals(x: Decimal) : Boolean {
        return when(value.isPresent && x.value.isPresent) {
            true -> value.get().compareTo(x.value.get()) == 0
            else -> false
        }
    }

    override operator fun compareTo(x: Decimal) : Int {
        return when(value.isPresent && x.value.isPresent) {
            true -> value.get().compareTo(x.value.get())
            else -> 0
        }
    }

    override fun toString() : String {
        return if ( value.isPresent ) value.get().toPlainString() else ""
    }
}