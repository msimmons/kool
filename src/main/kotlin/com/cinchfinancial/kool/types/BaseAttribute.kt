package com.cinchfinancial.kool.types

import com.cinchfinancial.kool.delegates.ModelProperty
import com.cinchfinancial.kool.types.InputContext
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