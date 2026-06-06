package com.tuempresa.safepass.ui.concurrency


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SimulationViewModel : ViewModel() {

    private val _uiState =
        MutableStateFlow<SimulationUiState>(SimulationUiState.Idle)

    val uiState: StateFlow<SimulationUiState> =
        _uiState.asStateFlow()

    fun cargarDatosAsincronos() {

        viewModelScope.launch {

            Log.d(
                "ANR_LAB",
                "Inicio Corrutina en hilo: ${Thread.currentThread().name}"
            )

            _uiState.value = SimulationUiState.Loading

            try {

                val resultado = withContext(Dispatchers.IO) {

                    Log.d(
                        "ANR_LAB",
                        "Procesando cálculo intensivo en segundo plano sobre hilo: ${Thread.currentThread().name}"
                    )

                    Thread.sleep(5000)

                    "Datos descargados con éxito desde segundo plano."
                }

                Log.d(
                    "ANR_LAB",
                    "Retorno seguro a Dispatchers.Main sobre hilo: ${Thread.currentThread().name}"
                )

                _uiState.value =
                    SimulationUiState.Success(resultado)

            } catch (e: Exception) {

                _uiState.value =
                    SimulationUiState.Error(
                        "Fallo asíncrono controlado: ${e.localizedMessage}"
                    )
            }
        }
    }
}