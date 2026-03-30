package uk.ideallygrey.lokomotion.ui.screens

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okio.IOException
import uk.ideallygrey.lokomotion.MainActivity
import uk.ideallygrey.lokomotion.network.TSWApi
import java.math.RoundingMode

var dtgKey: String = ""

fun setKey(key: String){
    dtgKey = key
}

sealed interface TSWUiState {
    data class Success(val simData: SimData): TSWUiState
    data class Error(val ioException: IOException): TSWUiState
    object Loading: TSWUiState
}

class TSWViewModel: ViewModel() {
    var tswUiState: TSWUiState by mutableStateOf(TSWUiState.Loading)
        private set


    init {
        pollData()
    }


    private fun pollData() {
        viewModelScope.launch {
            while (true) {
                if (dtgKey.isNotEmpty()) {
                    try {
                        val getSpeedResult = TSWApi.retrofitService.getSpeed(dtgKey)
                        val getDriverAidPlayerInfo = TSWApi.retrofitService.getDriverAidPlayerInfo(dtgKey)

                        tswUiState = TSWUiState.Success(
                            SimData(
                                speed = getSpeedResult.values.speed,
                                geoLocation = getDriverAidPlayerInfo.values.geoLocation,
                                playerServiceName = getDriverAidPlayerInfo.values.currentServiceName
                            )
                        )
                    } catch (e: IOException) {
                        tswUiState = TSWUiState.Error(e)
                    }
                }
                delay(100)
            }
        }
    }

    fun enableRailDriver() {
        viewModelScope.launch {
            if (dtgKey.isNotEmpty()) {
                var enabled = false
                while (!enabled) {
                    try {
                        TSWApi.retrofitService.enableRailDriver(dtgKey)
                        enabled = true
                    } catch (e: IOException) {
                    }
                }
            }
        }
    }

    fun setHorn() {
        viewModelScope.launch {
            if (dtgKey.isNotEmpty()) {
                var hornActive = false

                while (!hornActive) {
                    try {
                        TSWApi.retrofitService.setHornOn(dtgKey)
                        hornActive = true
                    } catch (e: IOException) {
                    }
                }
                delay(1000)
                while (hornActive) {
                    try {
                        TSWApi.retrofitService.setHornOff(dtgKey)
                        hornActive = false
                    } catch (e: IOException) {
                    }
                }
            }
        }
    }

}