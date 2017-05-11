package com.cinchfinancial.kool.inputs

import com.cinchfinancial.kool.types.Numeric
import com.fasterxml.jackson.annotation.JsonIgnore

typealias usd = Numeric

/**
 * Created by mark on 5/4/17.
 */
open class BaseInputs(@JsonIgnore val name: String, @JsonIgnore val context: InputContext) {

    protected val user_profile = context.userProfile
    protected val model_inputs = context.modelInputs

    inline fun <T : BaseInputs, reified V> T.formula(noinline formula: () -> V) : InputDelegate<T, V> {
        return InputDelegate<T, V>(V::class.java, context, formula)
    }
}