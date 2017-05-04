package com.cinchfinancial.kool.inputs

import java.util.*
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Created by mark on 3/4/17.
 */
class InputDelegate<T : BaseInputs, V>(val type: Class<V>, val formula: () -> V?) {

    lateinit var kprop: KProperty<*>
    lateinit var kref: Optional<T>
    private lateinit var delegate: ReadOnlyProperty<T, V>
    var exception = Optional.empty<Throwable>()
    val missingAttributeListener = MissingAttributeListener()
    val name : String
        get() = if (kref.isPresent) "${kref.get().name}.${kprop.name}" else kprop.name

    operator fun provideDelegate(thisRef: T, prop: KProperty<*>): ReadOnlyProperty<T, V> {
        kprop = prop
        kref = Optional.of(thisRef)
        delegate = object : ReadOnlyProperty<T, V> {
            var value = Optional.empty<V>()
            var computed: Boolean = false
            override fun getValue(thisRef: T, property: KProperty<*>): V {
                if (!computed) {
                    try {
                        missingAttributeListener.active = true
                        val theValue = formula()
                        missingAttributeListener.active = false
                        if (theValue != null) value = Optional.of(theValue)
                    } catch (e: Exception) {
                        exception = Optional.of(e)
                    }
                    computed = true
                }
                return value.orElse(null)
            }
        }
        thisRef.context.missingAttributeListeners.add(missingAttributeListener)
        return delegate
    }

    fun getValue(): V {
        return delegate.getValue(kref.get(), kprop)
    }
}