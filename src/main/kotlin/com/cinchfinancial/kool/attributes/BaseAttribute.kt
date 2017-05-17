package com.cinchfinancial.kool.attributes

import com.cinchfinancial.kool.delegates.ModelProperty
import com.cinchfinancial.kool.inputs.InputContext
import com.fasterxml.jackson.annotation.JsonIgnore


/**
 * Base class for all user attributes provides context and tracks
 * prefix for attribute naming
 */
open class BaseAttribute {

    @JsonIgnore
    lateinit var context : InputContext

    @JsonIgnore
    var prefix : String = ""

    fun addDependency(property: ModelProperty<*, *>) {
        context.addDependency(property)
    }

}