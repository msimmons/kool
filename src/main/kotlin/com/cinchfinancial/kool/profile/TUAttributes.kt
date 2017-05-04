package com.cinchfinancial.kool.profile

import java.math.BigDecimal

/**
 * Created by mark on 5/2/17.
 */
class TUAttributes(properties: Map<String, Any?>) : BaseAttributes(properties){

    val vantage_3_score: Int? by mapScalar(properties)
    val total_monthly_payments_revolving_cards: BigDecimal? by mapScalar(properties)
    val total_balance_revolving_cards: BigDecimal? by mapScalar(properties)
    val total_balance_open_trades: BigDecimal? by mapScalar(properties)
    val tax_liens: Int? by mapScalar(properties)
    val revolving_apr: BigDecimal? by mapScalar(properties)
    val likely_zero_percent_revolver: Boolean? by mapScalar(properties)
    val is_transactor: Boolean? by mapScalar(properties)
    val is_sloppy_behavior: Boolean? by mapScalar(properties)
    val is_revolver: Boolean? by mapScalar(properties)
    val fico_score: Int? by mapScalar(properties)
    val derogatory_public_records: Int? by mapScalar(properties)
    val delinquencies_2_years: Int? by mapScalar(properties)
    val debt_accounts: Int? by mapScalar(properties)
    val credit_inquiries_6_months: Int? by mapScalar(properties)
    val credit_history: Int? by mapScalar(properties)
    val bankruptcies_public: Int? by mapScalar(properties)
    val average_debt_service: BigDecimal? by mapScalar(properties)
    val age: Int? by mapScalar(properties)
    val address_zipcode: String? by mapScalar(properties)
    val address_state: String? by mapScalar(properties)
}
