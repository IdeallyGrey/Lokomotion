package uk.ideallygrey.lokomotion.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import uk.ideallygrey.lokomotion.ui.screens.HomeScreen
import uk.ideallygrey.lokomotion.ui.screens.TSWViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun LokomotionApp() {

    val tswViewModel: TSWViewModel = viewModel()
    HomeScreen(
        tswUiState = tswViewModel.tswUiState,
        tswViewModel = tswViewModel
    )

}

