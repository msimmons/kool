package com.cinchfinancial.kool.inputs

import com.cinchfinancial.kool.delegates.ModelInput
import com.cinchfinancial.kool.attributes.UserProfile
import com.cinchfinancial.kool.delegates.ModelProperty
import java.util.*

/**
 * Created by mark on 5/4/17.
 */
class InputContext(profile: Map<String, Any?>, val modelInputs: ModelInputs) {

    private val listeners = Stack<InputListener>()

    val inputDelegates = mutableSetOf<ModelInput<*, *>>()
    val userProfile = UserProfile(profile).apply {
        context = this@InputContext
    }

    fun pushListener(listener: InputListener) {
        listeners.push(listener)
    }

    fun popListener() {
        if ( !listeners.empty() ) listeners.pop()
    }

    fun addDependency(property: ModelProperty<*, *>) {
        if ( !listeners.empty() ) listeners.peek().addDependency(property)
    }

    fun computeAll() {
        inputDelegates.forEach {
            it.getValue()
            if (it.exception.isPresent) {
                println("${it.name} exception: ${it.exception.get()}")
            }
        }
    }
}