package com.cinchfinancial.kool.rule

import com.cinchfinancial.kool.inputs.InputListener
import com.cinchfinancial.kool.inputs.ModelInputs
import java.util.*

/**
 * Created by mark on 3/4/17.
 */
class RuleNode(val inputs: ModelInputs) : InputListener() {

    lateinit var formula : ()->Boolean
        private set
    val outcomes = mutableListOf<OutcomeNode>()
    var error = Optional.empty<Throwable>()
        private set
    val recommend = this

    fun formula(formula: () -> Boolean) {
        this.formula = formula
    }

    fun evaluate() : Boolean {
        try {
            inputs.context.pushListener(this)
            return formula()
        }
        catch (e:Exception) {
            error = Optional.of(e)
        }
        finally {
            inputs.context.popListener()
        }
        return false
    }

    infix fun outcome(name : String) : OutcomeNode {
        val outcome = OutcomeNode(name)
        outcomes.add(outcome)
        return outcome
    }

    infix fun against(name: String) : OutcomeNode {
        val str = OutcomeNode(name, false)
        outcomes.add(str)
        return str
    }

}