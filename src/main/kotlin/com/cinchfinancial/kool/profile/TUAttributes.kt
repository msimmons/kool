package com.cinchfinancial.kool.profile

import com.cinchfinancial.kool.types.Numeric

/**
 * Created by mark on 5/2/17.
 */
class TUAttributes(properties: Map<String, Any?>) : BaseAttributes(properties){

    val vantage_3_score: Numeric by scalarValue(properties)
    val total_monthly_payments_revolving_cards: Numeric by scalarValue(properties)
    val total_balance_revolving_cards: Numeric by scalarValue(properties)
    val total_balance_open_trades: Numeric by scalarValue(properties)
    val tax_liens: Numeric by scalarValue(properties)
    val revolving_apr: Numeric by scalarValue(properties)
    val likely_zero_percent_revolver: Boolean by scalarValue(properties)
    val is_transactor: Boolean by scalarValue(properties)
    val is_sloppy_behavior: Boolean by scalarValue(properties)
    val is_revolver: Boolean by scalarValue(properties)
    val fico_score: Numeric by scalarValue(properties)
    val derogatory_public_records: Numeric by scalarValue(properties)
    val delinquencies_2_years: Numeric by scalarValue(properties)
    val debt_accounts: Numeric by scalarValue(properties)
    val credit_inquiries_6_months: Numeric by scalarValue(properties)
    val credit_history: Numeric by scalarValue(properties)
    val bankruptcies_public: Numeric by scalarValue(properties)
    val average_debt_service: Numeric by scalarValue(properties)
    val age: Numeric by scalarValue(properties)
    val address_zipcode: String by scalarValue(properties)
    val address_state: String by scalarValue(properties)

}
