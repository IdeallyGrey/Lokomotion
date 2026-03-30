package uk.ideallygrey.lokomotion.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uk.ideallygrey.lokomotion.ui.screens.HomeScreen
import uk.ideallygrey.lokomotion.ui.screens.TSWViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import uk.ideallygrey.lokomotion.ui.screens.setKey


@Composable
fun LokomotionApp() {
    val tswViewModel: TSWViewModel = viewModel()
    Column(){
        KeyInput(tswViewModel)

        Button(
            modifier = Modifier.padding(32.dp),
            onClick = { tswViewModel.setHorn() }
        ) {
            Text("Horn")
        }

        HomeScreen(
            tswUiState = tswViewModel.tswUiState,
        )
    }

}

@Composable
fun KeyInput(tswViewModel: TSWViewModel) {
    var text by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(48.dp)
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,                   // current text from state
            onValueChange = { newText ->
                text = newText              // update the state on every keystroke
            },
            label = {
                Text("Enter API key")
            },
            leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = null)
            },
            singleLine = true,
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                setKey(text)
                tswViewModel.enableRailDriver()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Confirm")
        }

    }
}
