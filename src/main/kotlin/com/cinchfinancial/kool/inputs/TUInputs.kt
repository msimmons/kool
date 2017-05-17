package com.cinchfinancial.kool.inputs

import com.cinchfinancial.kool.delegates.formula
import com.cinchfinancial.kool.types.BaseInput
import com.cinchfinancial.kool.types.InputContext

/**
 * Created by mark on 5/4/17.
 */
class TUInputs(context: InputContext) : BaseInput("tu", context) {

    val revolving_apr by formula { user_profile.tu.revolving_apr }

}