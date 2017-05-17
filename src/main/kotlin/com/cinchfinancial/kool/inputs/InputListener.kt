package com.cinchfinancial.kool.inputs

import com.cinchfinancial.kool.delegates.ModelProperty

/**
 * Created by mark on 5/4/17.
 */
open class InputListener {

    val dependencies = mutableMapOf<String, ModelProperty<*, *>>()

    fun addDependency(property: ModelProperty<*, *>) {
        dependencies.put(property.name, property)
    }

}