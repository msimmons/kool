package com.cinchfinancial.kool.inputs

import com.cinchfinancial.kool.types.BaseType
import java.util.*
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * A [ReadOnlyProperty] delegate that supplies the value for a model input
 */
class ModelInput<T : BaseInput, V>(val parent : T, val property: KProperty<*>, val formula : () -> V) : InputEventListener(), ReadOnlyProperty<T,V> {

    var computed : Boolean = false
    val name : String = "${parent.name}.${property.name}"
    val context = parent.context
    var exception = Optional.empty<Throwable>()
    var value = Optional.empty<V>()

    init {
        context.inputEventListeners.add(this)
        context.inputDelegates.add(this)
    }

    /**
     * Delegate to the formula
     */
    override fun getValue(thisRef: T, kProperty: KProperty<*>): V {
        if (!computed) {
            try {
                active = true
                value = Optional.ofNullable(formula())
            } catch (e: Exception) {
                exception = Optional.of(e)
            }
            finally {
                computed = true
                active = false
            }
        }
        when (value.get()) {
            null -> context.addMissingInput(name)
            is BaseType -> if ( (value.get() as BaseType).isNull() ) context.addMissingInput(name)
        }
        return value.get()
    }

    /**
     * For calling manually outside of delegation mechanism
     */
    fun getValue(): V {
        return getValue(parent, property)
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