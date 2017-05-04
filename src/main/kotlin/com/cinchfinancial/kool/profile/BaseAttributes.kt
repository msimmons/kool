package com.cinchfinancial.kool.profile

import com.cinchfinancial.kool.inputs.InputContext
import com.fasterxml.jackson.annotation.JsonIgnore


/**
 * Base class for all user attributes provides context and tracks
 * prefix for attribute naming
 */
open class BaseAttributes(properties: Map<String, Any?>) {

    @JsonIgnore
    lateinit var context : InputContext

    @JsonIgnore
    var prefix : String = ""

    fun addMissingAttribute(attribute: String) {
        context.addMissingAttribute("$prefix$attribute")
    }

}