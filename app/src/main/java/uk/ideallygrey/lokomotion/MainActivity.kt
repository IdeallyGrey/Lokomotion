package uk.ideallygrey.lokomotion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import org.osmdroid.views.MapView

import uk.ideallygrey.lokomotion.ui.LokomotionApp
import uk.ideallygrey.lokomotion.ui.theme.LokomotionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LokomotionTheme {
                LokomotionApp()
            }
        }
    }

}

