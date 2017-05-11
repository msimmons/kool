package com.cinchfinancial.kool.profile

/**
 * Created by mark on 5/2/17.
 */
class UserAttributes(properties: Map<String, Any?>): BaseAttributes(properties){

    val unlinked_cash: usd by scalarValue(properties)
    val number_of_children: int by scalarValue(properties)
    val life_insurance_amount: usd by scalarValue(properties)
    val housing_status: string by scalarValue(properties)
    val has_shared_finances: bool by scalarValue(properties)
    val has_life_insurance: bool by scalarValue(properties)
    val has_children: bool by scalarValue(properties)
    val financial_goal: string by scalarValue(properties)
    val emotion_finances_today: int by scalarValue(properties)
    val earmarked_cash: usd by scalarValue(properties)
    val car_status: string by scalarValue(properties)
    val annual_individual_income: usd by scalarValue(properties)
    val annual_household_income: usd by scalarValue(properties)
    val youngest_child_age_years: int by scalarValue(properties)
    val weight_lbs: int by scalarValue(properties)
    val is_smoker: bool by scalarValue(properties)
    val height_inches: int by scalarValue(properties)
    val gender: string by scalarValue(properties)
    val employment_years: int by scalarValue(properties)

}