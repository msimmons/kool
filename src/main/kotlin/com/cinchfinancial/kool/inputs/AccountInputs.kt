package com.cinchfinancial.kool.inputs

import java.math.BigDecimal
import java.math.RoundingMode

/**
 * Created by mark on 5/4/17.
 */
class AccountInputs(context: InputContext) : BaseInputs("accounts", context) {

    val total_balance by formula {
        user_profile.accounts.map { it.user_input.balance }.reduce{ acc, value -> acc?.add(value)}
    }

    val max_balance by formula {
        user_profile.accounts.map { it.user_input.balance ?: BigDecimal.ZERO}.max()
    }

    val missing_one by formula {
        user_profile.accounts.filter { it.type == "credit_card" }.first()
    }

    val exception_one by formula {
        val size = BigDecimal.valueOf(user_profile.accounts.size.toLong())
        user_profile.accounts.map {
            it.user_input.balance?.divide(BigDecimal.ZERO, RoundingMode.HALF_UP) ?: 0
        }
    }

    val refers_to_other by formula {
        println(model_inputs.user.car_status)
        println(user_profile.id)
        model_inputs.user.car_status?.contains(user_profile.id ?: "")
    }
}