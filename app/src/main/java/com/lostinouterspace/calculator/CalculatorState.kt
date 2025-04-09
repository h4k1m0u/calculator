package com.lostinouterspace.calculator

// read-only fields since recomposition triggered *only* if state object re-assigned to
data class CalculatorState(
    val operand1: String = "",
    val operator: String = "",
    val operand2: String = ""
)
