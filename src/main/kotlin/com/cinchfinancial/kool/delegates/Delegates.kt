package com.cinchfinancial.kool.delegates

import com.cinchfinancial.kool.attributes.BaseAttribute
import com.cinchfinancial.kool.inputs.BaseInput
import com.cinchfinancial.kool.types.BaseType
import com.cinchfinancial.kool.types.Numeric
import com.cinchfinancial.kool.types.Text
import com.cinchfinancial.kool.types.Truth

typealias usd = Numeric
typealias percent = Numeric
typealias int = Numeric
typealias bool = Truth
typealias string = Text

/**
 * Returns a scalarValue value from the properties map
 */
inline fun <T : BaseAttribute, reified V : BaseType> T.scalarValue(properties: Map<String, Any?>): ModelProperty<T, V> {
    return ScalarProperty<T, V>(properties, V::class)
}

/**
 * Returns an object value from the properties array using the given factory
 * method to construct the object.  The object would be expected in turn to delegate
 * its properties to map which it points to
 */
fun <T : BaseAttribute, V : BaseAttribute> T.objectValue(
    properties: Map<String, Any?>,
    factory: (Map<String, Any?>) -> V
): ModelProperty<T, V> {
    return ObjectProperty<T, V>(properties, factory)
}

/**
 * Returns an array of scalarValue values pointed to by the given map key
 */
fun <T : BaseAttribute, V> T.scalarArray(properties: Map<String, Any?>): ModelProperty<T, Collection<V>> {
    return ScalarArrayProperty<T, V>(properties)
}

/**
 * Returns an array of object values pointed to by the given map key.  The objects are constructed using the given
 * factory method
 */
fun <T : BaseAttribute, V : BaseAttribute> T.objectArray(
    properties: Map<String, Any?>,
    factory: (Map<String, Any?>) -> V
): ModelProperty<T, Collection<V>> {
    return ObjectArrayProperty<T, V>(properties, factory)
}

/**
 * Return a [ModelInput.Provider] delegate provider for the given formula
 */
fun <T : BaseInput, V> T.formula(formula: () -> V) : ModelInput.Provider<T, V> {
    return ModelInput.Provider<T, V>(formula)
}
