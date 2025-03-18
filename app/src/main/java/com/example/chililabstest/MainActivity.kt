package com.example.chililabstest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.chililabstest.ui.navigation.Navigator
import com.example.chililabstest.ui.theme.ChiliLabsTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChiliLabsTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Navigator(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}