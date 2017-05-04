package com.cinchfinancial.kool.inputs

import com.cinchfinancial.kool.profile.UserProfile

/**
 * Created by mark on 5/4/17.
 */
class InputContext(profile: Map<String,Any?>, val modelInputs: ModelInputs) {

    val missingAttributes = mutableSetOf<String>()
    val missingAttributeListeners = mutableListOf<MissingAttributeListener>()

    val inputDelegates = mutableSetOf<InputDelegate<*,*>>()
    val userProfile = UserProfile(profile).apply {
        context = this@InputContext
    }

    fun addMissingAttribute(attribute: String) {
        missingAttributes.add(attribute)
        missingAttributeListeners.forEach { it.addMissingAttribute(attribute) }
    }

    fun computeAll() {
        inputDelegates.forEach {
            it.getValue()
            if (it.exception.isPresent) {
                println("${it.kprop.name} exception: ${it.exception.get()}")
            }
        }
    }
}