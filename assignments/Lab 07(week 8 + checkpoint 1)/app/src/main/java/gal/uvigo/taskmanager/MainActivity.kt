package gal.uvigo.taskmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import gal.uvigo.taskmanager.ui.MainScreen
import gal.uvigo.taskmanager.ui.theme.StickyNoteBoard
import gal.uvigo.taskmanager.Model.Task
import gal.uvigo.taskmanager.Model.Category
import java.time.LocalDate
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MainScreen()
                }
            }
        }
    }
}