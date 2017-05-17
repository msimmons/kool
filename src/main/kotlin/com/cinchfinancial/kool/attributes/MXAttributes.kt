package com.cinchfinancial.kool.attributes

import com.cinchfinancial.kool.types.int
import com.cinchfinancial.kool.delegates.objectValue
import com.cinchfinancial.kool.delegates.scalarValue
import com.cinchfinancial.kool.types.string
import com.cinchfinancial.kool.types.BaseAttribute

/**
 * Created by mark on 5/2/17.
 */
class MXAttributes(properties: Map<String, Any?>) : BaseAttribute() {

    val checking by objectValue(properties, MXAttributes::MXCheckingAttributes)

    class MXCheckingAttributes(properties: Map<String, Any?>) : BaseAttribute() {
        val temporal_context by objectValue(properties, MXAttributes::TemporalContext)
    }

    class TemporalContext(properties: Map<String, Any?>) : BaseAttribute() {
        val last_refresh_on: string by scalarValue(properties)
        val newest_transaction_on: string by scalarValue(properties)
        val oldest_transaction_on: string by scalarValue(properties)
        val months: int by scalarValue(properties)
        val days: int by scalarValue(properties)
    }
}
