package com.cinchfinancial.kool.inputs

import com.cinchfinancial.kool.types.InputType
import java.util.*
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Created by mark on 3/4/17.
 */
class InputDelegate<T : BaseInputs, V>(val klass: Class<V>, val type: InputType, val formula: () -> V?) : InputEventListener() {

    lateinit var kprop: KProperty<*>
    lateinit var kref: Optional<T>
    private lateinit var delegate: ReadOnlyProperty<T, V>
    var exception = Optional.empty<Throwable>()
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
                        this@InputDelegate.active = true
                        value = Optional.ofNullable(formula())
                    } catch (e: Exception) {
                        when (e) {
                            is MissingInputException -> this@InputDelegate.addMissingInput(e.name)
                            else -> exception = Optional.of(e)
                        }
                    }
                    finally {
                        this@InputDelegate.active = false
                    }
                    computed = true
                    if (!value.isPresent) throw MissingInputException(name)
                }
                return value.orElse(null)
            }
        }
        return delegate
    }

    fun getValue(): V {
        return delegate.getValue(kref.get(), kprop)
    }
}