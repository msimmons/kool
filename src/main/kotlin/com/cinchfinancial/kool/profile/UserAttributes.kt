package com.cinchfinancial.kool.profile

import java.math.BigDecimal

/**
 * Created by mark on 5/2/17.
 */
class UserAttributes(properties: Map<String, Any?>): BaseAttributes(properties){

    val unlinked_cash: BigDecimal? by scalarValue(properties)
    val number_of_children: Int? by scalarValue(properties)
    val life_insurance_amount: BigDecimal? by scalarValue(properties)
    val housing_status: String? by scalarValue(properties)
    val has_shared_finances: Boolean? by scalarValue(properties)
    val has_life_insurance: Boolean? by scalarValue(properties)
    val has_children: Boolean? by scalarValue(properties)
    val financial_goal: String? by scalarValue(properties)
    val emotion_finances_today: Int? by scalarValue(properties)
    val earmarked_cash: BigDecimal? by scalarValue(properties)
    val car_status: String? by scalarValue(properties)
    val annual_individual_income: BigDecimal? by scalarValue(properties)
    val annual_household_income: BigDecimal? by scalarValue(properties)
    val youngest_child_age_years: Int? by scalarValue(properties)
    val weight_lbs: Int? by scalarValue(properties)
    val is_smoker: Boolean? by scalarValue(properties)
    val height_inches: Int? by scalarValue(properties)
    val gender: String? by scalarValue(properties)
    val employment_years: Int? by scalarValue(properties)

}