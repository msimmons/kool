package com.cinchfinancial.kool.inputs

import com.cinchfinancial.kool.delegates.ModelProperty
import com.cinchfinancial.kool.types.InputContext
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
            .associate { Pair(it.name, it.exception.get().toString()) }
    }

    @JsonProperty("missing_dependencies")
    fun missingDependencies() : Map<String, List<ModelProperty<*, *>>> {
        return context.inputDelegates
                .filter { it.dependencies.any { it.value.missing }}
                .associate { Pair(it.name, it.dependencies.filter { it.value.missing }.map { it.value }) }
    }
}