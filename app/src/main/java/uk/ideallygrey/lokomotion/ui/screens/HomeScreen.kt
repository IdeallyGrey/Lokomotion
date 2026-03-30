package uk.ideallygrey.lokomotion.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.ideallygrey.lokomotion.ui.theme.LokomotionTheme

var latestData = SimData(null, null, null)
@Composable
fun HomeScreen(
    tswUiState: TSWUiState,
) {
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(32.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            when (tswUiState) {
                is TSWUiState.Loading -> LoadingScreen()
                is TSWUiState.Error -> ErrorScreen(tswUiState.ioException)
                is TSWUiState.Success -> ResultScreen(tswUiState.simData)
            }
        }
    }


}

@Composable
fun LoadingScreen() {
    Text("Loading")
}

@Composable
fun ErrorScreen(ioException: Exception) {
    Column(
        verticalArrangement = Arrangement.Center
    ) {
        //Text(text = "Error: $ioException")
        Text("Service: ${latestData.playerServiceName}")
        Text("Speed: ${latestData.speed}")
        Text("Lat: ${latestData.geoLocation?.latitude}")
        Text("Long: ${latestData.geoLocation?.longitude}")
    }
}

@Composable
fun ResultScreen(simData: SimData) {
    Column(
        verticalArrangement = Arrangement.Center
    ) {
        latestData = simData
        Text("Service: ${simData.playerServiceName}")
        Text("Speed: ${simData.speed}")
        Text("Lat: ${simData.geoLocation?.latitude}")
        Text("Long: ${simData.geoLocation?.longitude}")
    }
}





//@Preview(showBackground = true)
//@Composable
//fun HomeScreenPreview() {
//    LokomotionTheme {
//        HomeScreen()
//    }
//}