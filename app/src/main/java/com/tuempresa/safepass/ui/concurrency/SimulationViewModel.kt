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

    fun cargarDatosAsincronosConValidacion(textoUsuario: String) {

        viewModelScope.launch {

            _uiState.value = SimulationUiState.Loading

            try {

                if (textoUsuario.uppercase() == "ERROR") {
                    throw IllegalArgumentException(
                        "Entrada restringida detectada por simulación de seguridad."
                    )
                }

                val resultado = withContext(Dispatchers.IO) {

                    Thread.sleep(3000)

                    "Procesamiento asíncrono limpio completado."
                }

                _uiState.value =
                    SimulationUiState.Success(resultado)

            } catch (e: Exception) {

                _uiState.value =
                    SimulationUiState.Error(
                        e.localizedMessage ?: "Error desconocido"
                    )
            }
        }
    }
}