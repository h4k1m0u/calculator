package com.lostinouterspace.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.lostinouterspace.calculator.ui.theme.CalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // remember() makes the variable act like a static local var in C
            // i.e. initialized once and remembered for subsequent executions
            // persists across configuration changes (e.g. screen rotation)
            val state = rememberSaveable { mutableStateOf("") }

            CalculatorTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    ButtonsNumbers(state, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}