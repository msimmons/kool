package com.cinchfinancial.kool.inputs

/**
 * Created by mark on 5/4/17.
 */
class TUInputs(context: InputContext) : BaseInputs("tu", context) {

    val revolving_apr by formula { user_profile.tu.revolving_apr }

}