package com.lostinouterspace.calculator

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lostinouterspace.calculator.ui.theme.Orange

@Composable
fun CalculatorButtons(
    modifier: Modifier = Modifier,
    onClick: (CalculatorAction) -> Unit)
{
    Log.d("Calculator", "CalculatorButtons - Composing buttons...")

    val grid = listOf(
        listOf("C", "⌫", "%", "/"),
        listOf("7", "8", "9", "x"),
        listOf("4", "5", "6", "-"),
        listOf("1", "2", "3", "+"),
        listOf("0", ".", "="),
    )
    val operators = listOf("+", "-", "x", "/", "%")
    val buttonsSpacing = 10.dp

    val nRows = grid.size
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(buttonsSpacing),
    ) {
        repeat(nRows) { indexRow ->
            val nCols = grid[indexRow].size

            Row(
                horizontalArrangement = Arrangement.spacedBy(buttonsSpacing)
            ) {
                repeat(nCols) { indexCol ->
                    val cell = grid[indexRow][indexCol]
                    val action: CalculatorAction = when (cell) {
                        in operators -> CalculatorAction.Operator(cell)
                        "=" -> CalculatorAction.Calculate
                        "C" -> CalculatorAction.Clear
                        "⌫" -> CalculatorAction.Delete
                        else -> CalculatorAction.Number(cell)
                    }

                    // .aspectRatio() forces height to be equal to width,
                    // when combined with .weight() (which fixes width here) and .clip(CircleShape) below
                    val isLastCell = indexRow == nRows - 1 && indexCol == nCols - 1
                    CalculatorButton(
                        text = cell,
                        modifier = Modifier
                            .weight(if (isLastCell) 2.0f else 1.0f)
                            .aspectRatio(if (isLastCell) 2.0f else 1.0f),
                        color = if (cell in operators) Orange else Color.Black
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
    color: Color = Color.Black,
    onClick: () -> Unit = {}
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .clip(CircleShape),
        colors = ButtonDefaults.buttonColors(containerColor = color)
    ) {
        Text(
            text=text,
            fontSize = 32.sp,
        )
    }
}