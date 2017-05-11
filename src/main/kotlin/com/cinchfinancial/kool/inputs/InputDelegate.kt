package com.cinchfinancial.kool.inputs

import java.util.*
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Created by mark on 3/4/17.
 */
class InputDelegate<T : BaseInputs, V>(val klass: Class<V>, private val formula: () -> V) : InputEventListener() {

    private lateinit var kprop: KProperty<*>
    private lateinit var kref: T
    private lateinit var delegate: ReadOnlyProperty<T, V>

    var exception = Optional.empty<Throwable>()
    val name : String
        get() = "${kref.name}.${kprop.name}"

    operator fun provideDelegate(thisRef: T, prop: KProperty<*>): ReadOnlyProperty<T, V> {
        kprop = prop
        kref = thisRef
        delegate = object : ReadOnlyProperty<T, V> {
            var value = Optional.empty<V>()
            var computed: Boolean = false
            override fun getValue(thisRef: T, property: KProperty<*>): V {
                if (!computed) {
                    try {
                        this@InputDelegate.active = true
                        value = Optional.ofNullable(formula())
                    } catch (e: Exception) {
                            exception = Optional.of(e)
                    }
                    finally {
                        computed = true
                        if ( !value.isPresent ) this@InputDelegate.addMissingInput(name)
                        this@InputDelegate.active = false
                    }
                }
                return value.get()
            }
        }
        return delegate
    }

    fun getValue(): V {
        return delegate.getValue(kref, kprop)
    }
}