package com.cinchfinancial.kool.rule

import com.cinchfinancial.kool.inputs.InputEventListener
import com.cinchfinancial.kool.inputs.ModelInputs
import java.util.*

/**
 * Created by mark on 3/4/17.
 */
class RuleNode(val inputs: ModelInputs) : InputEventListener() {

    init {
        inputs.context.inputEventListeners.add(this)
    }

    lateinit var formula : ()->Boolean
        private set
    val outcomes = mutableListOf<OutcomeNode>()
    var error = Optional.empty<Throwable>()
        private set
    val recommend = this

    fun eval(eval: () -> Boolean) {
        formula = eval
    }

    fun evaluate() : Boolean {
        try {
            active = true
            return formula()
        }
        catch (e:Exception) {
            error = Optional.of(e)
        }
        finally {
            active = false
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