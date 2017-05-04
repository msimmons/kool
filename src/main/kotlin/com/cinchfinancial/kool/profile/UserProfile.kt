package com.cinchfinancial.kool.profile

class UserProfile(profile: Map<String, Any?>) : BaseAttributes(profile) {

    val id: String? by mapScalar(profile)
    val is_onboarded : Boolean? by mapScalar(profile)
    val user_input by mapObject(profile, ::UserAttributes)
    val tu by mapObject(profile, ::TUAttributes)
    val system by mapObject(profile, ::SystemAttributes)
    val mx by mapObject(profile, ::MXAttributes)
    val accounts : Collection<AccountAttributes> by mapObjectArray(profile, ::AccountAttributes)
    val missing_one : Boolean? by mapScalar(profile)

}