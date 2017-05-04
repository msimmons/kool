package com.cinchfinancial.kool.profile

import java.math.BigDecimal

/**
 * Created by mark on 5/2/17.
 */
class SystemAttributes(properties: Map<String, Any?>) : BaseAttributes(properties) {

    val locked_savings_lump_amount: BigDecimal? by properties
    val locked_savings_flow_amount: BigDecimal? by properties
    val locked_protection_flow_amount: BigDecimal? by properties
    val locked_debt_lump_amount: BigDecimal? by properties
    val locked_debt_flow_amount: BigDecimal? by properties
    val allocation_locked: Boolean? by properties

}
