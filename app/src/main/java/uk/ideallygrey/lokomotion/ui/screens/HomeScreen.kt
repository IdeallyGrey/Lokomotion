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

var latestSpeed = ""
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
//            when (tswUiState) {
//                is TSWUiState.Loading -> LoadingScreen()
//                is TSWUiState.Error -> ErrorScreen(error = tswUiState.error)
//                is TSWUiState.Success -> ResultScreen(photos = tswUiState.photos)
//            }
            when (tswUiState) {
                is TSWUiState.Loading -> LoadingScreen()
                is TSWUiState.Error -> ErrorScreen(error = tswUiState.error)
                is TSWUiState.Success -> ResultScreen(photos = tswUiState.photos)
            }
        }
    }


}

@Composable
fun LoadingScreen() {
    Text("Loading")
}

@Composable
fun ErrorScreen(error: String) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        //Text(text = "Error: $error")
        Text(text = "$latestSpeed E")
    }
}

@Composable
fun ResultScreen(photos: String) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        latestSpeed = photos
        Text(text = photos)
    }
}





//@Preview(showBackground = true)
//@Composable
//fun HomeScreenPreview() {
//    LokomotionTheme {
//        HomeScreen()
//    }
//}