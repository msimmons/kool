package com.cinchfinancial.kool.profile

import java.math.BigDecimal

/**
 * Created by mark on 5/2/17.
 */
class AccountAttributes(properties: Map<String, Any?>) : BaseAttributes(properties) {

    val id : String? by mapScalar(properties)
    val type : String? by mapScalar(properties)
    val user_input : UserInput by mapObject(properties, ::UserInput)

    class UserInput(properties: Map<String, Any?>) : BaseAttributes(properties) {
        val id : String? by mapScalar(properties)
        val source : String? by mapScalar(properties)
        val balance : BigDecimal? by mapScalar(properties)
        val is_new_account: Boolean? by mapScalar(properties)
    }
}
