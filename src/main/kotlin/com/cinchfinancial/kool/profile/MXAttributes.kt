package com.cinchfinancial.kool.profile

/**
 * Created by mark on 5/2/17.
 */
class MXAttributes(properties: Map<String, Any?>) : BaseAttributes(properties) {

    val checking by mapObject(properties, MXAttributes::MXCheckingAttributes)

    class MXCheckingAttributes(properties: Map<String, Any?>) : BaseAttributes(properties) {
        val temporal_context by mapObject(properties, MXAttributes::TemporalContext)
    }

    class TemporalContext(properties: Map<String, Any?>) : BaseAttributes(properties) {
        val last_refresh_on: String? by mapScalar(properties)
        val newest_transaction_on: String? by mapScalar(properties)
        val oldest_transaction_on: String? by mapScalar(properties)
        val months: Integer? by mapScalar(properties)
        val days: Integer? by mapScalar(properties)

    }
}
