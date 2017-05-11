package com.cinchfinancial.kool.rule

import com.cinchfinancial.kool.inputs.ModelInputs

/**
 * Created by mark on 3/4/17.
 */
class ModelNode(val inputs : ModelInputs) {

    val rules = mutableListOf<RuleNode>()

    fun rule(init: RuleNode.() ->Unit) : RuleNode {
        return RuleNode(inputs).apply {
            init()
            rules.add(this)
        }
    }

}

fun model(inputs: ModelInputs, init: ModelNode.() -> Unit) : ModelNode {
    return ModelNode(inputs).apply {
        init()
    }
}