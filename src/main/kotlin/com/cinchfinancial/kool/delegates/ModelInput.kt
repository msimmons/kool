package com.cinchfinancial.kool.delegates

import com.cinchfinancial.kool.inputs.BaseInput
import com.cinchfinancial.kool.inputs.InputListener
import com.cinchfinancial.kool.types.BaseType
import java.util.*
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * A [ReadOnlyProperty] delegate that supplies the value for a model input
 */
class ModelInput<T : BaseInput, V>(
        private val parent : T,
        private val property: KProperty<*>,
        private val formula : () -> V
) : InputListener(), ModelProperty<T, V> {

    private var computed : Boolean = false
    override val name = "${parent.name}.${property.name}"
    override val type = "input"
    override var missing = false
    private val context = parent.context
    var exception = Optional.empty<Throwable>()
    private var value = Optional.empty<V>()

    init {
        context.inputDelegates.add(this)
    }

    /**
     * Delegate to the formula
     */
    override fun getValue(thisRef: T, property: KProperty<*>): V {
        if (!computed) {
            try {
                thisRef.context.pushListener(this)
                value = Optional.ofNullable(formula())
            } catch (e: Exception) {
                exception = Optional.of(e)
            }
            finally {
                computed = true
                thisRef.context.popListener()
            }
        }
        val theValue = value.orElse(null)
        missing = when (theValue) {
            null -> true
            is BaseType -> theValue.isNull()
            else -> false
        }
        context.addDependency(this)
        return theValue
    }

    /**
     * For calling manually outside of delegation mechanism
     */
    fun getValue(): V {
        return getValue(parent, property)
    }

    /**
     * Print as a string
     */
    override fun toString() : String {
        return "$name [$type, $missing, $exception]"
    }

    /**
     * Provides this delegate, allows knowing the [thisRef] and [kProperty] before the delegate is ever called
     */
    class Provider<T : BaseInput, V>(val formula: () -> V) {
        operator fun provideDelegate(thisRef: T, kProperty: KProperty<*>): ReadOnlyProperty<T, V> {
            return ModelInput<T, V>(thisRef, kProperty, formula)
        }
    }
}