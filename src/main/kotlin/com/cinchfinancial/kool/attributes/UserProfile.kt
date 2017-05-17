package com.cinchfinancial.kool.attributes

import com.cinchfinancial.kool.delegates.*

class UserProfile(profile: Map<String, Any?>) : BaseAttribute() {

    val id: string by scalarValue(profile)
    val is_onboarded : bool by scalarValue(profile)
    val user_input by objectValue(profile, ::UserAttributes)
    val tu by objectValue(profile, ::TUAttributes)
    val system by objectValue(profile, ::SystemAttributes)
    val mx by objectValue(profile, ::MXAttributes)
    val accounts : Collection<AccountAttributes> by objectArray(profile, ::AccountAttributes)
    val missing_one : bool by scalarValue(profile)

}