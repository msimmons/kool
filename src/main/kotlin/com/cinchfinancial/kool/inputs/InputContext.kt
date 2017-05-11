package com.cinchfinancial.kool.inputs

import com.cinchfinancial.kool.profile.UserProfile

/**
 * Created by mark on 5/4/17.
 */
class InputContext(profile: Map<String,Any?>, val modelInputs: ModelInputs) {

    val missingAttributes = mutableSetOf<String>()
    val missingInputs = mutableSetOf<String>()
    val inputEventListeners = mutableListOf<InputEventListener>()

    val inputDelegates = mutableSetOf<InputDelegate<*,*>>()
    val userProfile = UserProfile(profile).apply {
        context = this@InputContext
    }

    fun addMissingAttribute(attribute: String) {
        missingAttributes.add(attribute)
        inputEventListeners.forEach { it.addMissingAttribute(attribute) }
    }

    fun addMissingInput(input: String) {
        missingInputs.add(input)
        inputEventListeners.forEach { it.addMissingInput(input) }
    }

    fun computeAll() {
        inputDelegates.forEach {
            try {
                it.getValue()
            }
            catch(e: MissingInputException) {
                println("Missing input: $e")
                addMissingInput(e.name)
            }
            if (it.exception.isPresent) {
                println("${it.name} exception: ${it.exception.get()}")
            }
        }
    }
}