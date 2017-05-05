package com.cinchfinancial.kool.profile

import com.cinchfinancial.kool.types.Numeric

/**
 * Created by mark on 5/2/17.
 */
class MXAttributes(properties: Map<String, Any?>) : BaseAttributes(properties) {

    val checking by objectValue(properties, MXAttributes::MXCheckingAttributes)

    class MXCheckingAttributes(properties: Map<String, Any?>) : BaseAttributes(properties) {
        val temporal_context by objectValue(properties, MXAttributes::TemporalContext)
    }

    class TemporalContext(properties: Map<String, Any?>) : BaseAttributes(properties) {
        val last_refresh_on: String by scalarValue(properties)
        val newest_transaction_on: String by scalarValue(properties)
        val oldest_transaction_on: String by scalarValue(properties)
        val months: Numeric by scalarValue(properties)
        val days: Numeric by scalarValue(properties)

    }
}
