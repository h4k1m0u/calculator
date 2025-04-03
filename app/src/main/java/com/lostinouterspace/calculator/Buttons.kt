package com.lostinouterspace.calculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ButtonsNumbers(state: MutableState<String>, modifier: Modifier = Modifier) {
    val grid = listOf(
        listOf("7", "8", "9", "/"),
        listOf("4", "5", "6", "*"),
        listOf("1", "2", "3", "-"),
        listOf("0", ".", "=", "+"),
    )
    val nRows = grid.size

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(state.value)

        repeat(nRows) { indexRow ->
            val nCols = grid[indexRow].size
            Row {
                repeat(nCols) { indexCol ->
                    ButtonNumber(
                        text=grid[indexRow][indexCol],
                        onClick={ state.value += grid[indexRow][indexCol] }
                    )
                }
            }
        }
    }
}

@Composable
private fun ButtonNumber(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        modifier = modifier,
    ) {
        Text(
            text=text,
        )
    }
}