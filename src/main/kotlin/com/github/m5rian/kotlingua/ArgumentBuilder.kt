package com.github.m5rian.kotlingua

/**
 *
 */
class ArgumentBuilder {
    val args = mutableMapOf<String, Any>()
    fun arg(key: String, value: Any) = run { args[key] = value }
}