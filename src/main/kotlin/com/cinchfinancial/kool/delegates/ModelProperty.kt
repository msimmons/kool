package com.cinchfinancial.kool.delegates

import kotlin.properties.ReadOnlyProperty

/**
 * Created by mark on 5/15/17.
 */
interface ModelProperty<T, V> : ReadOnlyProperty<T, V> {

    val name : String
    val type: String
    val missing: Boolean
}