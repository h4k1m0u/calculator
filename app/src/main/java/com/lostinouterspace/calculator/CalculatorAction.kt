package com.lostinouterspace.calculator

/**
 * like enums but can hold state (e.g. number typed)
 * data class/object adds auto-generated methods like toString(), equals()...
 * `object` actually declares singleton instances (not types)
 * `object` doesn't have a constructor => used when no state needed (data class cannot be used for that, as at least one c'tor arg. needed)
 */
sealed class CalculatorAction {
    data class Number(val value: Int) : CalculatorAction()
    data object Clear : CalculatorAction()
    data object None : CalculatorAction()
}