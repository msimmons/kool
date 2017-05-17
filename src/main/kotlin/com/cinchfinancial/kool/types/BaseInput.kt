package com.cinchfinancial.kool.types

import com.fasterxml.jackson.annotation.JsonIgnore

/**
 * Created by mark on 5/4/17.
 */
open class BaseInput(@JsonIgnore val name: String, @JsonIgnore val context: InputContext) {

    protected val user_profile = context.userProfile
    protected val model_inputs = context.modelInputs
}