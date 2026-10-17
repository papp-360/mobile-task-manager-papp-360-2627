package gal.uvigo.taskmanager.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import gal.uvigo.taskmanager.Model.Category
import gal.uvigo.taskmanager.Model.Task
import gal.uvigo.taskmanager.R
import java.time.LocalDate
@Composable
fun MainScreen() {
    // -- Paso 2: lista observable. mutableStateListOf ya es "Compose-aware" por sí misma,
    //    no necesita envolverse en mutableStateOf adicional --
    val tasks = remember {
        mutableStateListOf(
            Task(1, "Buy groceries", "Milk, eggs, bread", LocalDate.now().minusDays(2), Category.PERSONAL),
            Task(2, "Finish Kotlin lab", "", LocalDate.now(), Category.WORK, isDone = true),
            Task(3, "Team meeting", "Discuss sprint planning", LocalDate.now().plusDays(3), Category.URGENT)
        )
    }
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = stringResource(R.string.title_welcome),
            style = MaterialTheme.typography.headlineSmall
        )
    // -- Paso 3: resumen derivado, SIN remember propio
        val completedCount = tasks.count{ it.isDone }
        Text(
            text = "Completed: $completedCount of ${tasks.size} tasks",
            style = MaterialTheme.typography.bodyMedium
        )
        // -- Paso 4: formulario de alta (lo añadimos en el siguiente bloque) --
        AddTaskForm(
            onAddTask = { newTask -> tasks.add(newTask) }
        )
        // -- Lista, ahora con los eventos conectados --
        for(task in tasks){
            TaskItem(
                task = task,
                onToggleDone =  {
                    val index = tasks.indexOf(task)
                    if(index != -1){
                        tasks[index] = task.copy(isDone = !task.isDone)
                    }
                },
                onDelete = {tasks.remove(task)}
            )
        }
    }
}