package com.cinchfinancial.kool.rule

import com.cinchfinancial.kool.inputs.ModelInputs
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import io.kotlintest.specs.BehaviorSpec
import java.io.File

/**
 * Created by mark on 5/10/17.
 */
class ModelNodeSpec : BehaviorSpec() {

    init {
        val objectMapper = ObjectMapper()
        objectMapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT)

        Given("User attributes and model inputs") {
            val profile = objectMapper.readValue(File("src/test/resources/snapshot.json"), Map::class.java) as Map<String, Any>
            val inputs = ModelInputs(profile)
            val theModel = model(inputs) {
                rule {
                    formula {
                        inputs.tu.revolving_apr > 0 || inputs.accounts.effective_apr.any { it > 0 }
                    }
                    recommend outcome "O1" because "R1"
                    recommend against "O2" because "R2"
                }
            }
            Then("Evaluate the rules") {
                theModel.rules.forEach {
                    it.evaluate() shouldBe true
                    it.error.isPresent shouldBe false
                    it.dependencies.size shouldEqual 2
                    //it.dependencies should contain("tu.revolving_apr")
                    println(it.dependencies)
                }
            }
        }
    }
}