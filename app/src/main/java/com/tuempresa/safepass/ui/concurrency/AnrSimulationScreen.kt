package com.tuempresa.safepass.ui.concurrency

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AnrSimulationScreen(
    modifier: Modifier = Modifier,
    simulationViewModel: SimulationViewModel = viewModel()
) {
    var textInput by remember { mutableStateOf("") }

    val currentUiState by simulationViewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Laboratorio Concurrencia Avanzada - Apps Fluidas",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = textInput,
            onValueChange = { textInput = it },
            label = {
                Text("Escriba aquí para comprobar que la UI NO SE CONGELA")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                simulationViewModel.cargarDatosAsincronosConValidacion(textInput)
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = currentUiState !is SimulationUiState.Loading
        ) {
            Text("Cargar Datos Pesados (Asíncrono y Seguro)")
        }

        Spacer(modifier = Modifier.height(24.dp))

        when (currentUiState) {

            is SimulationUiState.Idle -> {
                Text("Estado: Esperando acción del usuario")
            }

            is SimulationUiState.Loading -> {
                CircularProgressIndicator()
                Spacer(modifier = Modifier.height(8.dp))
                Text("Procesando en segundo plano en Dispatchers.IO...")
            }

            is SimulationUiState.Success -> {
                Text(
                    text = "Resultado: ${
                        (currentUiState as SimulationUiState.Success).dataMessage
                    }",
                    color = MaterialTheme.colorScheme.primary
                )
            }

            is SimulationUiState.Error -> {
                Text(
                    text = "Error: ${
                        (currentUiState as SimulationUiState.Error).errorMessage
                    }",
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}
