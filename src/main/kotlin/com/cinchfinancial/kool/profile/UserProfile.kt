package com.cinchfinancial.kool.profile

class UserProfile(profile: Map<String, Any?>) : BaseAttributes(profile) {

    val id: String? by scalarValue(profile)
    val is_onboarded : Boolean? by scalarValue(profile)
    val user_input by objectValue(profile, ::UserAttributes)
    val tu by objectValue(profile, ::TUAttributes)
    val system by objectValue(profile, ::SystemAttributes)
    val mx by objectValue(profile, ::MXAttributes)
    val accounts : Collection<AccountAttributes> by objectArray(profile, ::AccountAttributes)
    val missing_one : Boolean? by scalarValue(profile)

}