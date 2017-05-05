package com.cinchfinancial.kool.inputs

/**
 * Created by mark on 5/4/17.
 */
class MXInputs(context: InputContext) : BaseInputs("mx", context) {

    val last_refresh_on by formula(usd) {user_profile.mx.checking.temporal_context.last_refresh_on}
    val months by formula(int) { user_profile.mx.checking.temporal_context.months }
}