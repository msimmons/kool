package com.cinchfinancial.kool.inputs

import com.cinchfinancial.kool.types.Numeric

/**
 * Created by mark on 5/4/17.
 */
class UserInputs(context: InputContext) : BaseInputs("user", context) {

    val onboarded by formula(bool) { user_profile.is_onboarded }
    val missing_one by formula(usd) {user_profile.missing_one}
    val has_children by formula(bool) { user_profile.user_input.has_children }
    val car_status by formula(string) {user_profile.user_input.car_status}
    val null_one by formula(usd) { Numeric() }
    val reference_null_one by formula(usd) { null_one / 0 }
    val owns_house by formula(bool) { user_profile.user_input.housing_status.equals("own") }
    val a_decimal_one by formula(usd) { user_profile.system.locked_debt_flow_amount }
}