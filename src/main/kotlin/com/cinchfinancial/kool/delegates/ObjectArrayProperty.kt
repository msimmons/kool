package com.cinchfinancial.kool.delegates

import com.cinchfinancial.kool.attributes.BaseAttribute
import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*
import kotlin.reflect.KProperty

/**
 * Created by mark on 5/16/17.
 */
class ObjectArrayProperty<T : BaseAttribute, V : BaseAttribute>(
        private val properties: Map<String, Any?>,
        private val factory: (Map<String, Any?>) -> V
) : ModelProperty<T, Collection<V>> {
    override lateinit var name: String
    override val type = "attribute"
    override var missing = false
    private var collection = Optional.empty<Collection<V>>()

    @JsonIgnore
    override fun getValue(thisRef: T, property: KProperty<*>): Collection<V> {
        name = "${thisRef.prefix}${property.name}"
        if (!collection.isPresent) {
            missing = !properties.containsKey(property.name)
            val value = properties.getOrDefault(property.name, listOf<Map<String,Any?>>()) as List<Map<String,Any?>>
            collection = Optional.of(value.mapIndexed { index, it ->
                factory(it).apply {
                    this.prefix = "${thisRef.prefix}${property.name}[$index]."
                    this.context = thisRef.context
                }
            })
        }
        thisRef.addDependency(this)
        return collection.get()
    }
}