package com.cinchfinancial.kool.attributes

import com.cinchfinancial.kool.types.bool
import com.cinchfinancial.kool.delegates.scalarValue
import com.cinchfinancial.kool.types.usd
import com.cinchfinancial.kool.types.BaseAttribute

/**
 * Created by mark on 5/2/17.
 */
class SystemAttributes(properties: Map<String, Any?>) : BaseAttribute() {

    val locked_savings_lump_amount: usd by scalarValue(properties)
    val locked_savings_flow_amount: usd by scalarValue(properties)
    val locked_protection_flow_amount: usd by scalarValue(properties)
    val locked_debt_lump_amount: usd by scalarValue(properties)
    val locked_debt_flow_amount: usd by scalarValue(properties)
    val allocation_locked: bool by scalarValue(properties)

}
