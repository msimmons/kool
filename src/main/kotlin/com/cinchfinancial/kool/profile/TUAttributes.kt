package com.cinchfinancial.kool.profile

import java.math.BigDecimal

/**
 * Created by mark on 5/2/17.
 */
class TUAttributes(properties: Map<String, Any?>) : BaseAttributes(properties){

    val vantage_3_score: Int? by scalarValue(properties)
    val total_monthly_payments_revolving_cards: BigDecimal? by scalarValue(properties)
    val total_balance_revolving_cards: BigDecimal? by scalarValue(properties)
    val total_balance_open_trades: BigDecimal? by scalarValue(properties)
    val tax_liens: Int? by scalarValue(properties)
    val revolving_apr: BigDecimal? by scalarValue(properties)
    val likely_zero_percent_revolver: Boolean? by scalarValue(properties)
    val is_transactor: Boolean? by scalarValue(properties)
    val is_sloppy_behavior: Boolean? by scalarValue(properties)
    val is_revolver: Boolean? by scalarValue(properties)
    val fico_score: Int? by scalarValue(properties)
    val derogatory_public_records: Int? by scalarValue(properties)
    val delinquencies_2_years: Int? by scalarValue(properties)
    val debt_accounts: Int? by scalarValue(properties)
    val credit_inquiries_6_months: Int? by scalarValue(properties)
    val credit_history: Int? by scalarValue(properties)
    val bankruptcies_public: Int? by scalarValue(properties)
    val average_debt_service: BigDecimal? by scalarValue(properties)
    val age: Int? by scalarValue(properties)
    val address_zipcode: String? by scalarValue(properties)
    val address_state: String? by scalarValue(properties)

}
