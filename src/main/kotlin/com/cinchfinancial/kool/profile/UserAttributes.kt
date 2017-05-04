package com.cinchfinancial.kool.profile

import java.math.BigDecimal

/**
 * Created by mark on 5/2/17.
 */
class UserAttributes(properties: Map<String, Any?>): BaseAttributes(properties){

    val unlinked_cash: BigDecimal? by mapScalar(properties)
    val number_of_children: Int? by mapScalar(properties)
    val life_insurance_amount: BigDecimal? by mapScalar(properties)
    val housing_status: String? by mapScalar(properties)
    val has_shared_finances: Boolean? by mapScalar(properties)
    val has_life_insurance: Boolean? by mapScalar(properties)
    val has_children: Boolean? by mapScalar(properties)
    val financial_goal: String? by mapScalar(properties)
    val emotion_finances_today: Int? by mapScalar(properties)
    val earmarked_cash: BigDecimal? by mapScalar(properties)
    val car_status: String? by mapScalar(properties)
    val annual_individual_income: BigDecimal? by mapScalar(properties)
    val annual_household_income: BigDecimal? by mapScalar(properties)
}