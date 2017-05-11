package com.cinchfinancial.kool.inputs

import com.cinchfinancial.kool.types.BaseType
import java.util.*
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Created by mark on 3/4/17.
 */
class InputDelegate<T : BaseInputs, V>(val klass: Class<V>, private val context: InputContext, private val formula: () -> V) : InputEventListener() {

    init {
        context.inputDelegates.add(this)
        context.inputEventListeners.add(this)
    }

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
            lateinit var value : Optional<V>
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
                        this@InputDelegate.active = false
                    }
                }
                when (value.get()) {
                    null -> context.addMissingInput(name)
                    is BaseType -> if ( (value.get() as BaseType).isNull() ) context.addMissingInput(name)
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