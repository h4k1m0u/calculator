package com.lostinouterspace.calculator;

// read-only fields since recomposition triggered *only* if state object re-assigned to
data class CalculatorState(
    val operand1: Int? = null,
    val operator: Char? = null,
    val operand2: Int? = null
)
