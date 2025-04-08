package com.lostinouterspace.calculator

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorScreen(state: CalculatorState, modifier: Modifier = Modifier) {
    Log.d("Calculator", "CalculatorScreen - displaying screen...")

    val operand1 = state.operand1?.toString() ?: "_"
    val operand2 = state.operand2?.toString() ?: "_"
    val operator = state.operator?.toString() ?: "_"

    Text(
        "$operand1 $operator $operand2",
        modifier = modifier,
        fontSize = 64.sp
    )
}