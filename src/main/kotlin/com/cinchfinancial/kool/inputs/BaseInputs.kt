package com.cinchfinancial.kool.inputs

import com.cinchfinancial.kool.types.InputType
import com.fasterxml.jackson.annotation.JsonIgnore

/**
 * Created by mark on 5/4/17.
 */
open class BaseInputs(@JsonIgnore val name: String, @JsonIgnore val context: InputContext) {

    protected val usd = InputType.usd
    protected val bool = InputType.bool
    protected val percent = InputType.percent
    protected val string = InputType.string
    protected val int = InputType.int
    protected val obj = InputType.obj

    protected val user_profile = context.userProfile
    protected val model_inputs = context.modelInputs

    inline fun <T : BaseInputs, reified V> T.formula(type: InputType, noinline formula: () -> V) : InputDelegate<T, V> {
        return InputDelegate<T, V>(V::class.java, type, formula).apply {
            context.inputDelegates.add(this)
        }
    }
}