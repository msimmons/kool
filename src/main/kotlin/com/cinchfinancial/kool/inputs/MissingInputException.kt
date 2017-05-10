package com.cinchfinancial.kool.inputs

/**
 * Created by mark on 5/9/17.
 */
class MissingInputException(val name: String) : RuntimeException("Missing input $name")