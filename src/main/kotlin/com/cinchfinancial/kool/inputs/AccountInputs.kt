package com.cinchfinancial.kool.inputs

/**
 * Created by mark on 5/4/17.
 */
class AccountInputs(context: InputContext) : BaseInputs("accounts", context) {

    val total_balance by formula(usd) {
        user_profile.accounts.map { it.user_input.balance }.reduce{ acc, value -> acc + value}
    }

    val max_balance by formula(usd) {
        user_profile.accounts.map { it.user_input.balance }.maxBy { it }
    }

    val missing_one by formula(obj) {
        user_profile.accounts.filter { it.type == "credit_card" }.first()
    }

    val exception_one by formula(usd) {
        println(user_profile.accounts.size)
        user_profile.accounts.map {
            it.user_input.balance / user_profile.accounts.size
        }
    }

    val refers_to_other by formula(bool) {
        println(model_inputs.user.car_status)
        println(user_profile.id)
        model_inputs.user.car_status?.contains(user_profile.id)
    }
}