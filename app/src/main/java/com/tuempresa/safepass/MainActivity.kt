package com.tuempresa.safepass

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.tuempresa.safepass.ui.concurrency.AnrSimulationScreen
import com.tuempresa.safepass.ui.theme.SafePass2026Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SafePass2026Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AnrSimulationScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}