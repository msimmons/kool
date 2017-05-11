package com.cinchfinancial.kool.profile

/**
 * Created by mark on 5/2/17.
 */
class AccountAttributes(properties: Map<String, Any?>) : BaseAttributes(properties) {

    val id : string by scalarValue(properties)
    val type : string by scalarValue(properties)
    val user_input : UserInput by objectValue(properties, ::UserInput)
    val tu : TU by objectValue(properties, ::TU)
    val mx : MX by objectValue(properties, ::MX)

    class UserInput(properties: Map<String, Any?>) : BaseAttributes(properties) {
        val id : string by scalarValue(properties)
        val source : string by scalarValue(properties)
        val balance : usd by scalarValue(properties)
        val is_new_account: bool by scalarValue(properties)
    }

    class TU(properties: Map<String, Any?>) : BaseAttributes(properties) {
        val history by objectArray(properties, ::TUHistory)
        val summary by objectValue(properties, ::TUSummary)
        val last_payment: usd by scalarValue(properties)
        val last_statement_balance: usd by scalarValue(properties)
        val payment_profile_started_on: string by scalarValue(properties)
        val closed_on: string by scalarValue(properties)
        val balance: usd by scalarValue(properties)
        val is_transactor: bool by scalarValue(properties)
        val is_open: bool by scalarValue(properties)
        val is_new_account: bool by scalarValue(properties)
        val is_credit_card: bool by scalarValue(properties)
        val is_sloppy_behavior: bool by scalarValue(properties)
        val opened_on: string by scalarValue(properties)
        val monthly_payment_type: string by scalarValue(properties)
        val monthly_payment: usd by scalarValue(properties)
        val delinquencies_90_days: int by scalarValue(properties)
        val portfolio_type: string by scalarValue(properties)
        val name: string by scalarValue(properties)
        val credit_limit: usd by scalarValue(properties)
        val months_history: int by scalarValue(properties)
        val delinquencies_30_days: int by scalarValue(properties)
        val revolving_apr: bool by scalarValue(properties)
        val last_paid_on: string by scalarValue(properties)
        val closed_indicator: string by scalarValue(properties)
        val type: string by scalarValue(properties)
        val responsibility: string by scalarValue(properties)
        val high_credit: usd by scalarValue(properties)
        val delinquencies_60_days: int by scalarValue(properties)
        val id: string by scalarValue(properties)
        val last_statement_month: string by scalarValue(properties)
        val effective_on: string by scalarValue(properties)
        val likely_zero_percent_revolver: bool by scalarValue(properties)
        val payment_profile: string by scalarValue(properties)
        val industry_code: string by scalarValue(properties)
        val is_revolver: bool by scalarValue(properties)
        val number: string by scalarValue(properties)
        val source: string by scalarValue(properties)
    }

    class TUHistory(properties: Map<String, Any?>) : BaseAttributes(properties) {
        val scheduled_monthly_payment: usd by scalarValue(properties)
        val past_due: bool by scalarValue(properties)
        val month_effective: string by scalarValue(properties)
        val month_available: bool by scalarValue(properties)
        val high_credit: usd by scalarValue(properties)
        val current_balance: usd by scalarValue(properties)
        val credit_limit: usd by scalarValue(properties)
        val actual_payment: usd by scalarValue(properties)
    }

    class TUSummary(properties: Map<String, Any?>) : BaseAttributes(properties) {
        val scheduled_monthly_payment_amount: usd by scalarValue(properties)
        val most_recent_payment_amount: usd by scalarValue(properties)
        val effective_date: string by scalarValue(properties)
    }

    class MX(properties: Map<String, Any?>) : BaseAttributes(properties) {
        val balance : usd by scalarValue(properties)
    }
}
