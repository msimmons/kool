package com.cinchfinancial.kool.delegates

import com.cinchfinancial.kool.attributes.BaseAttribute
import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*
import kotlin.reflect.KProperty

/**
 * Created by mark on 5/16/17.
 */
class ScalarArrayProperty<T : BaseAttribute, V>(private val properties: Map<String, Any?>): ModelProperty<T, Collection<V>> {
    override lateinit var name : String
    override val type = "attribute"
    override var missing = false
    private var collection = Optional.empty<Collection<V>>()

    @JsonIgnore
    override fun getValue(thisRef: T, property: KProperty<*>): Collection<V> {
        name = "${thisRef.prefix}${property.name}"
        if (!collection.isPresent) {
            missing = !properties.containsKey(property.name)
            val theCollection = properties.getOrDefault(property.name, listOf<V>()) as Collection<V>
            collection = Optional.of(theCollection)
        }
        thisRef.addDependency(this)
        return collection.get()
    }
}
