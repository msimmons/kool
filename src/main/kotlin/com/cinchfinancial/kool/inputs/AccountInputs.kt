package com.cinchfinancial.kool.inputs

import com.cinchfinancial.kool.delegates.formula
import com.cinchfinancial.kool.types.BaseInput
import com.cinchfinancial.kool.types.FALSE
import com.cinchfinancial.kool.types.InputContext

/**
 * Created by mark on 5/4/17.
 */
class AccountInputs(context: InputContext) : BaseInput("accounts", context) {

    val total_balance by formula {
        user_profile.accounts.map { it.user_input.balance }.reduce{ acc, value -> acc + value}
    }

    val max_balance by formula {
        user_profile.accounts.map { it.user_input.balance }.maxBy { it }
    }

    val missing_one by formula {
        user_profile.accounts.filter { it.type EQ "credit_car" }
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

    val effective_apr by formula {
        user_profile.accounts.filter { it.type EQ "credit_card" }.map {
            val require_user_apr = !it.tu.revolving_apr.isNull() && !it.mx.effective_apr.isNull() &&
                it.tu.revolving_apr - it.mx.effective_apr > 0.05
            if ( require_user_apr ) it.user_input.reported_apr
            else {
                val aprs = listOf(it.tu.revolving_apr, it.mx.effective_apr, it.user_input.reported_apr)
                aprs.firstOrNull { !it.isNull() } ?: it.user_input.reported_apr
            }
        }
    }

    val likely_revolver by formula {
        user_profile.accounts.filter { it.type EQ "credit_card" }.map {
            val hasBalance = !it.mx.balance.isNull() && it.mx.balance > 0
            if ( hasBalance ) !it.user_input.pay_in_full_each_month
            else FALSE
        }
    }
}