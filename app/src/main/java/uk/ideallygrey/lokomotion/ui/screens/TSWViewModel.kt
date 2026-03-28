package uk.ideallygrey.lokomotion.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import okio.IOException
import uk.ideallygrey.lokomotion.network.TSWApi

val dtgKey: String = "123abc"

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


    public fun getPhotos() {
        viewModelScope.launch {
            tswUiState = try {
                val listResult = TSWApi.retrofitService.getPhotos(dtgKey)
                //TSWUiState.Success("Success: ${listResult.size} Mars photos retrieved")
                TSWUiState.Success("Success: ${listResult}")
            } catch (e: IOException) {
                TSWUiState.Error(e.toString())
            }
        }
    }
}