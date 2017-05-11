package com.cinchfinancial.kool.profile

/**
 * Created by mark on 5/2/17.
 */
class TUAttributes(properties: Map<String, Any?>) : BaseAttributes(properties){

    val vantage_3_score: int by scalarValue(properties)
    val total_monthly_payments_revolving_cards: usd by scalarValue(properties)
    val total_balance_revolving_cards: usd by scalarValue(properties)
    val total_balance_open_trades: usd by scalarValue(properties)
    val tax_liens: int by scalarValue(properties)
    val revolving_apr: percent by scalarValue(properties)
    val likely_zero_percent_revolver: bool by scalarValue(properties)
    val is_transactor: bool by scalarValue(properties)
    val is_sloppy_behavior: bool by scalarValue(properties)
    val is_revolver: bool by scalarValue(properties)
    val fico_score: int by scalarValue(properties)
    val derogatory_public_records: int by scalarValue(properties)
    val delinquencies_2_years: int by scalarValue(properties)
    val debt_accounts: int by scalarValue(properties)
    val credit_inquiries_6_months: int by scalarValue(properties)
    val credit_history: int by scalarValue(properties)
    val bankruptcies_public: int by scalarValue(properties)
    val average_debt_service: usd by scalarValue(properties)
    val age: int by scalarValue(properties)
    val address_zipcode: string by scalarValue(properties)
    val address_state: string by scalarValue(properties)

}
