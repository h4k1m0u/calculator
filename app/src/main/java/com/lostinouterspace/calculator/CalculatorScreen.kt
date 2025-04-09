package com.lostinouterspace.calculator

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorScreen(state: CalculatorState, modifier: Modifier = Modifier) {
    Log.d("Calculator", "CalculatorScreen - displaying screen...")

    // without line-height, digits from subsequent rows will run into each other due to font-size
    Text(
        "${state.operand1} ${state.operator} ${state.operand2}",
        modifier = modifier,
        fontSize = 64.sp,
        lineHeight = 64.sp
    )
}