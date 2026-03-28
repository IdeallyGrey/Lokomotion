package uk.ideallygrey.lokomotion.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okio.IOException
import uk.ideallygrey.lokomotion.network.TSWApi
import java.math.RoundingMode

var dtgKey: String = ""

fun setKey(newKey: String) {
    dtgKey = newKey
}

sealed interface TSWUiState {
    data class Success(val photos: String): TSWUiState
    data class Error(val error: String): TSWUiState
    object Loading: TSWUiState
}

class TSWViewModel: ViewModel() {
    var tswUiState: TSWUiState by mutableStateOf(TSWUiState.Loading)
        private set

    init {
        getPhotos()
    }


    private fun getPhotos() {
        viewModelScope.launch {
            while (true) {
                if (dtgKey != "") {
                    tswUiState = try {
                        val listResult = TSWApi.retrofitService.getPhotos(dtgKey)
                        TSWUiState.Success("Velocity (km/h): ${(listResult.values.speed / 1000 * 60 * 60).toBigDecimal().setScale(1, RoundingMode.HALF_UP).toDouble()}")
                    } catch (e: IOException) {
                        TSWUiState.Error(e.toString())
                    }
                }
                delay(250)
            }
        }
    }
}