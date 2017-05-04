package com.cinchfinancial.kool.profile

import java.math.BigDecimal
import java.util.*
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Created by mark on 5/3/17.
 */
/**
 * Returns a scalarValue value from the properties map
 */
inline fun <T : BaseAttributes, reified V> T.scalarValue(properties: Map<String, Any?>): ReadOnlyProperty<T, V?> {
    return object : ReadOnlyProperty<T, V?> {
        private var value = Optional.empty<V>()
        override fun getValue(thisRef: T, property: KProperty<*>) : V? {
            if ( !value.isPresent ) {
                val theValue = properties.get(property.name)
                when (theValue) {
                    null -> thisRef.addMissingAttribute(property.name)
                    is V -> value = Optional.of(theValue)
                    is Int -> when (V::class) {
                        is BigDecimal -> value = Optional.of(BigDecimal.valueOf(theValue.toLong()) as V)
                    }
                    else -> throw IllegalStateException("${thisRef.prefix}${property.name} expected ${V::class.java} but got ${theValue.javaClass}")
                }
            }
            return value.orElse(null)
        }
    }
}

/**
 * Returns an object value from the properties array using the given factory
 * method to construct the object.  The object would be expected in turn to delegate
 * its properties to map which it points to
 */
fun <T : BaseAttributes, V : BaseAttributes> T.objectValue(
    properties: Map<String, Any?>,
    factory: (Map<String,Any?>) -> V
): ReadOnlyProperty<T, V>
{
    return object : ReadOnlyProperty<T, V> {
        private var mapObject = Optional.empty<V>()
        override fun getValue(thisRef: T, property: KProperty<*>) : V {
            if ( !mapObject.isPresent ) {
                if ( properties.get(property.name) == null ) thisRef.addMissingAttribute(property.name)
                val subMap : Map<String,Any?> = properties.get(property.name) as Map<String,Any?>? ?: mapOf<String,Any?>()
                mapObject = Optional.of(factory(subMap))
                mapObject.get().apply {
                    context = thisRef.context
                    prefix = "${thisRef.prefix}${property.name}."
                }
            }
            return mapObject.get()
        }
    }
}

/**
 * Returns an array of scalarValue values pointed to by the given map key
 */
fun <T : BaseAttributes, V> T.scalarArray(properties: Map<String, Any?>): ReadOnlyProperty<T, Collection<V>> {
    return object : ReadOnlyProperty<T, Collection<V>> {
        private var collection = Optional.empty<Collection<V>>()
        override fun getValue(thisRef: T, property: KProperty<*>) : Collection<V> {
            if ( !collection.isPresent ) {
                if ( properties.get(property.name) == null ) thisRef.addMissingAttribute(property.name)
                val theCollection : Collection<V> = properties.get(property.name) as Collection<V>? ?: listOf<V>()
                collection = Optional.of(theCollection)
            }
            return collection.get()
        }
    }
}

/**
 * Returns an array of object values pointed to by the given map key.  The objects are constructed using the given
 * factory method
 */
fun <T : BaseAttributes, V : BaseAttributes> T.objectArray(
    properties: Map<String, Any?>,
    factory: (Map<String,Any?>) -> V
): ReadOnlyProperty<T, Collection<V>>
{
    return object : ReadOnlyProperty<T, Collection<V>> {
        private var collection = Optional.empty<Collection<V>>()
        override fun getValue(thisRef: T, property: KProperty<*>) : Collection<V> {
            if ( !collection.isPresent ) {
                if ( properties.get(property.name) == null ) thisRef.addMissingAttribute(property.name)
                val value : Collection<Map<String,Any?>> = properties.get(property.name) as Collection<Map<String, Any?>>? ?: listOf<Map<String,Any?>>()
                collection = Optional.of(value.mapIndexed { index, it ->
                    factory(it).apply {
                        this.prefix = "${thisRef.prefix}${property.name}[$index]."
                        this.context = thisRef.context
                    }
                })
            }
            return collection.get()
        }
    }
}
