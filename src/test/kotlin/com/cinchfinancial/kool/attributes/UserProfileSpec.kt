package com.cinchfinancial.kool.attributes

import com.cinchfinancial.kool.inputs.InputContext
import com.cinchfinancial.kool.inputs.ModelInputs
import com.cinchfinancial.kool.types.Numeric
import com.cinchfinancial.kool.types.Text
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import io.kotlintest.specs.BehaviorSpec
import java.io.File

/**
 * Created by mark on 5/2/17.
 */
class UserProfileSpec : BehaviorSpec() {

    init {
        val objectMapper = ObjectMapper()
        objectMapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT)

        Given("Some user attributes") {
            val profile = objectMapper.readValue(File("src/test/resources/snapshot.json"), Map::class.java) as Map<String,Any>
            val inputs = ModelInputs(profile)

            Then("the user attributes object is cool") {
                val context = InputContext(profile, ModelInputs(profile))
                context.userProfile.accounts.size shouldEqual 3
            }

            Then("it has the input") {
                inputs.user.has_children shouldBe true
                inputs.context.computeAll()
                inputs.tu.revolving_apr shouldEqual Numeric()
                inputs.mx.last_refresh_on shouldEqual Text()
                println(inputs.accounts.effective_apr)

                println(objectMapper.writeValueAsString(inputs))
            }
        }

    }
}