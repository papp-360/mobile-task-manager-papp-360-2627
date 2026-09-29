package gal.uvigo.taskmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import gal.uvigo.taskmanager.ui.theme.TaskManagerTheme
import gal.uvigo.taskmanager.Model.Task
import gal.uvigo.taskmanager.Model.Category
import java.time.LocalDate

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskManagerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TaskManagerTheme {
        Greeting("Android")
    }
}

@Composable
fun TaskManagerScreen() {
    // -- Paso 2: lista dummy de tareas --
    val dummyTasks = listOf(
        Task(
            id = 1,
            title = "Buy groceries",
            description = "Milk, eggs, bread",
            dueDate = LocalDate.now(),
            category = Category.PERSONAL
        ),
        Task(
            id = 2,
            title = "Finish Kotlin lab",
            description = "",
            dueDate = LocalDate.now(),
            category = Category.WORK
        ),
        Task(
            id = 3,
            title = "Team meeting",
            description = "Discuss sprint planning",
            dueDate = LocalDate.now(),
            category = Category.URGENT
        )
    )
}