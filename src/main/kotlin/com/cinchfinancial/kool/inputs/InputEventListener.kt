package com.cinchfinancial.kool.inputs

/**
 * Created by mark on 5/4/17.
 */
open class InputEventListener {

    var active = false
    val missingAttributes = mutableSetOf<String>()
    val missingInputs = mutableSetOf<String>()

    fun addMissingAttribute(attribute: String) {
        if ( active ) missingAttributes.add(attribute)
    }

    fun addMissingInput(input: String) {
        if ( active ) missingInputs.add(input)
    }

}