package com.cinchfinancial.kool.inputs

/**
 * Created by mark on 5/4/17.
 */
class UserInputs(context: InputContext) : BaseInputs("user", context) {

    val onboarded by formula(bool) { user_profile.is_onboarded }
    val missing_one by formula(usd) {user_profile.missing_one}
    val has_children by formula(bool) { user_profile.user_input.has_children }
    val car_status by formula(string) {user_profile.user_input.car_status}
}