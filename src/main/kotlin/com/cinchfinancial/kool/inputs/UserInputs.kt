package com.cinchfinancial.kool.inputs

import com.cinchfinancial.kool.delegates.formula

/**
 * Created by mark on 5/4/17.
 */
class UserInputs(context: InputContext) : BaseInput("user", context) {

    val onboarded by formula { user_profile.is_onboarded }
    val missing_one by formula {user_profile.missing_one}
    val has_children by formula { user_profile.user_input.has_children }
    val car_status by formula {user_profile.user_input.car_status}
    val null_one by formula { user_profile.tu.vantage_3_score }
    val reference_null_one by formula { null_one / 0 }
    val owns_house by formula { user_profile.user_input.housing_status.equals("own") }
    val a_decimal_one by formula { user_profile.system.locked_debt_flow_amount }
}