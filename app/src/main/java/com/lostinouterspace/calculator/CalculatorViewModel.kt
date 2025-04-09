package com.lostinouterspace.calculator

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.util.Locale

/**
 * State holder that persists through config changes (e.g. screen rotation)
 * Used also to handle UI events (e.g. buttons clicks)
 * must reassign to state in event handlers, since modifying data class field won't trigger a recomposition (e.g. state.operand1 = value)
 */
class CalculatorViewModel : ViewModel() {
    // private set to prevent property from being modified outside viewmodel
    var state by mutableStateOf(CalculatorState())
        private set

    fun onClick(action: CalculatorAction) {
        when (action) {
            is CalculatorAction.Number -> enterNumber(action.value)
            is CalculatorAction.Operator -> enterOperation(action.value)
            is CalculatorAction.Calculate -> calculate()
            is CalculatorAction.Clear -> clear()
            is CalculatorAction.Delete -> delete()
        }
    }

    private fun enterNumber(value: String) {
        Log.d("Calculator", "ViewModel - Updating state")

        // whether operator was typed determines if we're typing operand1 or operand2
        if (state.operator.isEmpty()) {
            // prevent adding two dots to the same number
            if (value == "." && state.operand1.contains('.'))
                return

            state = state.copy(operand1 = state.operand1 + value)
            return
        }

        if (value == "." && state.operand2.contains('.'))
            return

        state = state.copy(operand2 = state.operand2 + value)
    }

    private fun delete() {
        if (state.operator.isEmpty()) {
            state = state.copy(operand1 = state.operand1.dropLast(1))
            return
        }

        state = state.copy(operand2 = state.operand2.dropLast(1))
    }

    private fun enterOperation(value: String) {
        state = state.copy(operator = value)
    }

    private fun calculate() {
        if (state.operand1.isEmpty() || state.operator.isEmpty() || state.operand2.isEmpty())
            return

        val result: Double = when (state.operator) {
            "+" -> state.operand1.toDouble() + state.operand2.toDouble()
            "-" -> state.operand1.toDouble() - state.operand2.toDouble()
            "*" -> state.operand1.toDouble() * state.operand2.toDouble()
            "/" -> state.operand1.toDouble() / state.operand2.toDouble()
            "%" -> state.operand1.toDouble() % state.operand2.toDouble()
            else -> 0.0
        }

        val resultString = if (isRoundNumber(result))
            result.toInt().toString()
        else
            String.format(Locale.US, "%.5f", result)

        // remove decimal if is a round number
        state = state.copy(
            operand1 = resultString,
            operator = "",
            operand2 = "",
        )
    }

    /* Convert double to int (get rid of decimal) if it's a round number */
    private fun isRoundNumber(x: Double): Boolean {
        return x == x.toInt().toDouble()
    }

    private fun clear() {
        state = CalculatorState()
    }
}