package com.cinchfinancial.kool.delegates

import com.cinchfinancial.kool.attributes.BaseAttribute
import com.cinchfinancial.kool.types.Numeric
import com.cinchfinancial.kool.types.Text
import com.cinchfinancial.kool.types.Truth
import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

/**
 * Created by mark on 5/16/17.
 */
class ScalarProperty<T : BaseAttribute, V>(
        private val properties: Map<String, Any?>,
        private val kClass: KClass<*>
) : ModelProperty<T, V> {
    override lateinit var name: String
    override val type = "attribute"
    override var missing = false
    private var value = Optional.empty<V>()

    @JsonIgnore
    override fun getValue(thisRef: T, property: KProperty<*>): V {
        name = "${thisRef.prefix}${property.name}"
        if (!value.isPresent) {
            val theValue = properties.get(property.name)
            missing = (theValue == null)
            value = when (kClass) {
                Text::class -> Optional.of(Text(theValue as String?) as V)
                Numeric::class -> Optional.of(Numeric(theValue as Number?) as V)
                Truth::class -> Optional.of(Truth(theValue as Boolean?) as V)
                else -> throw IllegalArgumentException("Unknown $kClass for ${thisRef.prefix}${property.name}")
            }
        }
        thisRef.addDependency(this)
        return value.get()
    }
}