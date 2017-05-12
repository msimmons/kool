package com.cinchfinancial.kool.inputs

import com.cinchfinancial.kool.types.formula

/**
 * Created by mark on 5/4/17.
 */
class MXInputs(context: InputContext) : BaseInput("mx", context) {

    val last_refresh_on by formula {user_profile.mx.checking.temporal_context.last_refresh_on}
    val months by formula { user_profile.mx.checking.temporal_context.months }
}