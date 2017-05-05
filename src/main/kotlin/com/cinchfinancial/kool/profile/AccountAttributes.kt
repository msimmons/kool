package com.cinchfinancial.kool.profile

import com.cinchfinancial.kool.types.Decimal

/**
 * Created by mark on 5/2/17.
 */
class AccountAttributes(properties: Map<String, Any?>) : BaseAttributes(properties) {

    val id : String by scalarValue(properties)
    val type : String by scalarValue(properties)
    val user_input : UserInput by objectValue(properties, ::UserInput)
    val tu : TU by objectValue(properties, ::TU)

    class UserInput(properties: Map<String, Any?>) : BaseAttributes(properties) {
        val id : String by scalarValue(properties)
        val source : String by scalarValue(properties)
        val balance : Decimal by scalarValue(properties)
        val is_new_account: Boolean by scalarValue(properties)
    }

    class TU(properties: Map<String, Any?>) : BaseAttributes(properties) {
        val history by objectArray(properties, ::TUHistory)
        val summary by objectArray(properties, ::TUSummary)
        val last_payment: Decimal by scalarValue(properties)
        val last_statement_balance: Decimal by scalarValue(properties)
        val payment_profile_started_on: String by scalarValue(properties)
        val closed_on: String by scalarValue(properties)
        val balance: Decimal by scalarValue(properties)
        val is_transactor: Boolean by scalarValue(properties)
        val is_open: Boolean by scalarValue(properties)
        val is_new_account: Boolean by scalarValue(properties)
        val is_credit_card: Boolean by scalarValue(properties)
        val is_sloppy_behavior: Boolean by scalarValue(properties)
        val opened_on: String by scalarValue(properties)
        val monthly_payment_type: String by scalarValue(properties)
        val monthly_payment: Decimal by scalarValue(properties)
        val delinquencies_90_days: Int by scalarValue(properties)
        val portfolio_type: String by scalarValue(properties)
        val name: String by scalarValue(properties)
        val credit_limit: Decimal by scalarValue(properties)
        val months_history: Int by scalarValue(properties)
        val delinquencies_30_days: Int by scalarValue(properties)
        val revolving_apr: Boolean by scalarValue(properties)
        val last_paid_on: String by scalarValue(properties)
        val closed_indicator: String by scalarValue(properties)
        val type: String by scalarValue(properties)
        val responsibility: String by scalarValue(properties)
        val high_credit: Decimal by scalarValue(properties)
        val delinquencies_60_days: Int by scalarValue(properties)
        val id: String by scalarValue(properties)
        val last_statement_month: String by scalarValue(properties)
        val effective_on: String by scalarValue(properties)
        val likely_zero_percent_revolver: Boolean by scalarValue(properties)
        val payment_profile: String by scalarValue(properties)
        val industry_code: String by scalarValue(properties)
        val is_revolver: Boolean by scalarValue(properties)
        val number: String by scalarValue(properties)
        val source: String by scalarValue(properties)
    }

    class TUHistory(properties: Map<String, Any?>) : BaseAttributes(properties) {
        val scheduled_monthly_payment: Decimal by scalarValue(properties)
        val past_due: Boolean by scalarValue(properties)
        val month_effective: String by scalarValue(properties)
        val month_available: Boolean by scalarValue(properties)
        val high_credit: Decimal by scalarValue(properties)
        val current_balance: Decimal by scalarValue(properties)
        val credit_limit: Decimal by scalarValue(properties)
        val actual_payment: Decimal by scalarValue(properties)
    }

    class TUSummary(properties: Map<String, Any?>) : BaseAttributes(properties) {
        val scheduled_monthly_payment_amount: Decimal by scalarValue(properties)
        val most_recent_payment_amount: Decimal by scalarValue(properties)
        val effective_date: String by scalarValue(properties)
    }

    class MX(properties: Map<String, Any?>) : BaseAttributes(properties) {

    }
}
