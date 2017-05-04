package com.cinchfinancial.kool.profile

import com.cinchfinancial.kool.inputs.InputContext
import com.cinchfinancial.kool.inputs.ModelInputs
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

        Given("Some user profile") {
            val profile = objectMapper.readValue(File("src/test/resources/compact-snapshot.json"), Map::class.java) as Map<String,Any>
            val inputs = ModelInputs(profile)

            Then("the user profile object is cool") {
                val context = InputContext(profile, ModelInputs(profile))
                context.userProfile.accounts.size shouldEqual 14
            }

            Then("it has the input") {
                inputs.user.has_children shouldBe true
                inputs.context.computeAll()
                inputs.tu.revolving_apr shouldEqual null
                inputs.mx.last_refresh_on shouldEqual null

                println(objectMapper.writeValueAsString(inputs))
            }
        }

    }
}