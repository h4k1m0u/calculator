package com.lostinouterspace.calculator

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

/**
 * State holder that persists through config changes (e.g. screen rotation)
 * Used also to handle UI events (e.g. buttons clicks)
 */
class CalculatorViewModel : ViewModel() {
    // private sett to prevent property from being modified outside viewmodel
    var state by mutableStateOf(CalculatorState())
        private set

    fun onClick(action: CalculatorAction) {
        when (action) {
            is CalculatorAction.Number -> enterNumber(action.value)
            is CalculatorAction.Clear -> clear()
            else -> println()
        }
    }

    private fun enterNumber(value: Int) {
        Log.d("Calculator", "ViewModel - Updating state")
        // must reassign to state, since modifying data class field won't trigger a recomposition (e.g. state.operand1 = value)
        state = state.copy(operand1 = value)
    }

    private fun clear() {
        state = CalculatorState()
    }
}