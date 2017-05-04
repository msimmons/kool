package com.cinchfinancial.kool.inputs

/**
 * Created by mark on 5/4/17.
 */
class MissingAttributeListener {

    var active = false
    val missingAttributes = mutableSetOf<String>()

    fun addMissingAttribute(attribute: String) {
        if ( active ) missingAttributes.add(attribute)
    }
}