package com.lostinouterspace.calculator

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.lostinouterspace.calculator.ui.theme.CalculatorTheme
import com.lostinouterspace.calculator.ui.theme.SaladGreen

class MainActivity : ComponentActivity() {
    // viewModels() returns a Lazy delegate (i.e. calculated only once)
    private val viewModel: CalculatorViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = { Text("Calculator") }
                        )
                    }
                ) { innerPadding ->
                    val paddingContainer = 10.dp

                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                            .background(Color.LightGray),
                        // verticalArrangement = Arrangement.Center,
                        // horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // ViewModel shouldn't be passed down to composables only its state & actions (testability & decoupling)
                        // https://developer.android.com/develop/ui/compose/migrate/other-considerations#viewmodel
                        Log.d("Calculator", "MainActivity - reading state...")

                        CalculatorScreen(
                            state = viewModel.state,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(SaladGreen)
                                .padding(paddingContainer)
                        )

                        CalculatorButtons(
                            onClick = { action: CalculatorAction -> viewModel.onClick(action) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.DarkGray)
                                .padding(paddingContainer)
                        )
                    }
                }
            }
        }
    }
}