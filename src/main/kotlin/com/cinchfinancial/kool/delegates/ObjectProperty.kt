package com.cinchfinancial.kool.delegates

import com.cinchfinancial.kool.attributes.BaseAttribute
import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*
import kotlin.reflect.KProperty

/**
 * Created by mark on 5/16/17.
 */
class ObjectProperty<T : BaseAttribute, V : BaseAttribute>(
        private val properties: Map<String, Any?>,
        private val factory: (Map<String, Any?>) -> V
) : ModelProperty<T, V> {

    override lateinit var name: String
    override val type = "attribute"
    override var missing = false

    private var mapObject = Optional.empty<V>()

    @JsonIgnore
    override fun getValue(thisRef: T, property: KProperty<*>): V {
        name = "${thisRef.prefix}${property.name}"
        if (!mapObject.isPresent) {
            missing = !properties.containsKey(property.name)
            val subMap = properties.getOrDefault(property.name, mapOf<String,Any?>()) as Map<String, Any?>
            mapObject = Optional.of(factory(subMap).apply {
                context = thisRef.context
                prefix = "${thisRef.prefix}${property.name}."
            })
        }
        thisRef.addDependency(this)
        return mapObject.get()
    }
}