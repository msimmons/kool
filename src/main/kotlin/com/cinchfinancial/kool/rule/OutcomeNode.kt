package com.cinchfinancial.kool.rule

/**
 * Created by mark on 3/4/17.
 */
class OutcomeNode(val name : String, val recommended : Boolean = true) {

    private val attributes = mutableMapOf<String,Any>()

    lateinit var reason : String
        private set

    infix fun because(reason: String) : OutcomeNode {
        this.reason=reason;
        return this
    }

    infix fun properties(entries: () -> Unit) : OutcomeNode {
        return this
    }

}