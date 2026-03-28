package uk.ideallygrey.lokomotion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.ideallygrey.lokomotion.ui.LokomotionApp
import uk.ideallygrey.lokomotion.ui.screens.HomeScreen
import uk.ideallygrey.lokomotion.ui.screens.TSWViewModel
import uk.ideallygrey.lokomotion.ui.screens.setKey
import uk.ideallygrey.lokomotion.ui.theme.LokomotionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LokomotionTheme {
                KeyInput()
                LokomotionApp()
            }
        }
    }
}

@Composable
fun KeyInput() {
    var text by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(48.dp)
    ) {
        TextField(
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
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Confirm")
        }

    }
}