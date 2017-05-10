package com.cinchfinancial.kool.profile

import com.cinchfinancial.kool.types.Numeric
import com.cinchfinancial.kool.types.Text

/**
 * Created by mark on 5/2/17.
 */
class UserAttributes(properties: Map<String, Any?>): BaseAttributes(properties){

    val unlinked_cash: Numeric by scalarValue(properties)
    val number_of_children: Numeric by scalarValue(properties)
    val life_insurance_amount: Numeric by scalarValue(properties)
    val housing_status: Text by scalarValue(properties)
    val has_shared_finances: Boolean by scalarValue(properties)
    val has_life_insurance: Boolean by scalarValue(properties)
    val has_children: Boolean by scalarValue(properties)
    val financial_goal: Text by scalarValue(properties)
    val emotion_finances_today: Numeric by scalarValue(properties)
    val earmarked_cash: Numeric by scalarValue(properties)
    val car_status: Text by scalarValue(properties)
    val annual_individual_income: Numeric by scalarValue(properties)
    val annual_household_income: Numeric by scalarValue(properties)
    val youngest_child_age_years: Numeric by scalarValue(properties)
    val weight_lbs: Numeric by scalarValue(properties)
    val is_smoker: Boolean by scalarValue(properties)
    val height_inches: Numeric by scalarValue(properties)
    val gender: Text by scalarValue(properties)
    val employment_years: Numeric by scalarValue(properties)

}