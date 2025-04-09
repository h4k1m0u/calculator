package com.lostinouterspace.calculator

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CalculatorButtons(
    modifier: Modifier = Modifier,
    onClick: (CalculatorAction) -> Unit)
{
    Log.d("Calculator", "CalculatorButtons - Composing buttons...")

    val grid = listOf(
        listOf("C", "⌫", "%", "/"),
        listOf("7", "8", "9", "*"),
        listOf("4", "5", "6", "-"),
        listOf("1", "2", "3", "+"),
        listOf("0", ".", "="),
    )
    val nRows = grid.size
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        repeat(nRows) { indexRow ->
            val nCols = grid[indexRow].size

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                repeat(nCols) { indexCol ->
                    val cell = grid[indexRow][indexCol]
                   val action: CalculatorAction = when (cell) {
                       "+", "-", "*", "/", "%" -> CalculatorAction.Operator(cell)
                       "=" -> CalculatorAction.Calculate
                       "C" -> CalculatorAction.Clear
                       "⌫" -> CalculatorAction.Delete
                        else -> CalculatorAction.Number(cell)
                    }

                    CalculatorButton(
                        text = cell,
                        modifier = Modifier
                            .size(100.dp)
                            .weight(if (indexRow == nRows - 1 && indexCol == nCols - 1) 2.0f else 1.0f)
                    ) {
                        onClick(action)
                    }
                }
            }
        }
    }
}

@Composable
private fun CalculatorButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
         colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
    ) {
        Text(
            text=text,
        )
    }
}