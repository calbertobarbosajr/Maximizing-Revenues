package com.calberto_barbosa_jr.maximizingrevenues

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.calberto_barbosa_jr.maximizingrevenues.ui.theme.MaximizingRevenuesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaximizingRevenuesTheme {
                SimulationScreen()
            }
        }
    }
}