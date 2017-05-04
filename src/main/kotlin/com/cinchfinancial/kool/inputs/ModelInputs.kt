package com.cinchfinancial.kool.inputs

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Created by mark on 5/1/17.
 */
class ModelInputs(profile: Map<String,Any>) {

    @JsonIgnore
    val context = InputContext(profile, this)

    val user = UserInputs(context)
    val tu = TUInputs(context)
    val mx = MXInputs(context)
    val accounts = AccountInputs(context)

    @JsonProperty("input_exceptions")
    fun getExceptions() : Map<String, String?> {
        return context.inputDelegates
            .filter { it.exception.isPresent }
            .associate { Pair(it.kprop.name, it.exception.get().toString()) }
    }

    @JsonProperty("missing_attributes")
    fun foo() : Map<String, Set<String>> {
        return context.inputDelegates
            .filter { it.missingAttributeListener.missingAttributes.size > 0 }
            .associate { Pair(it.name, it.missingAttributeListener.missingAttributes) }
    }
}