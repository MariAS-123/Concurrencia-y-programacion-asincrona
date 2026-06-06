package com.tuempresa.safepass.ui.concurrency

/**
 * Jerarquía sellada que representa de manera exhaustiva los estados lógicos
 * posibles de la interfaz gráfica durante el proceso de simulación de carga de datos.
 */
sealed class SimulationUiState {
    data object Idle : SimulationUiState()
    data object Loading : SimulationUiState()
    data class Success(val dataMessage: String) :
        SimulationUiState()
    data class Error(val errorMessage: String) : SimulationUiState()
}