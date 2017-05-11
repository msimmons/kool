package com.cinchfinancial.kool.inputs

/**
 * Created by mark on 5/4/17.
 */
class AccountInputs(context: InputContext) : BaseInputs("accounts", context) {

    val total_balance by formula {
        user_profile.accounts.map { it.user_input.balance }.reduce{ acc, value -> acc + value}
    }

    val max_balance by formula {
        user_profile.accounts.map { it.user_input.balance }.maxBy { it }
    }

    val missing_one by formula {
        user_profile.accounts.filter { it.type.equals("credit_car") }
    }

    val exception_one by formula {
        user_profile.accounts.map {
            it.user_input.balance / user_profile.accounts.size
        }
    }

    val refers_to_other by formula {
        model_inputs.user.car_status.contains(user_profile.id)
    }

    val lets_try_this_one by formula {
        user_profile.accounts.map {  if ( it.tu.balance.isNull() ) it.mx.balance else it.tu.balance }
    }
}