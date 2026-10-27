package gal.uvigo.taskmanager.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import gal.uvigo.taskmanager.Model.Category
import gal.uvigo.taskmanager.Model.Task
import gal.uvigo.taskmanager.R
import java.time.LocalDate
import androidx.compose.foundation.background
import gal.uvigo.taskmanager.ui.AddTaskForm
@Composable
fun MainScreen() {

    val tasks = remember {
        mutableStateListOf(
            Task(1, "Buy groceries", "Milk, eggs, bread", LocalDate.now().minusDays(2), Category.PERSONAL),
            Task(2, "Finish Kotlin lab", "", LocalDate.now(), Category.WORK, isDone = true),
            Task(3, "Team meeting", "Discuss sprint planning", LocalDate.now().plusDays(3), Category.WORK)
        )
    }

    Column(modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)) {

        Spacer(modifier = Modifier.height(16.dp))
        Text(stringResource(id = R.string.title_welcome), style = MaterialTheme.typography.headlineSmall)

        val completedCount = tasks.count { it.isDone }
        Text("Completed: $completedCount of ${tasks.size} tasks", style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(8.dp))

        AddTaskForm(onAddTask = { newTask -> tasks.add(newTask) })

        Spacer(modifier = Modifier.height(8.dp))

        // -- Paso 4: Empty State, patrón declarativo simple --
        if (tasks.isEmpty()) {
            EmptyTasksState(modifier = Modifier.weight(1f))
        } else {
            // -- Paso 5: agrupamos ANTES de pasar los datos a la UI --
            val pending = tasks.filter { !it.isDone }
            val done = tasks.filter { it.isDone }

            LazyColumn(
                modifier = Modifier.weight(1f).fillMaxWidth(),
                // -- Paso 3: contentPadding en vez de Modifier.padding (no recorta el área scrolleable) --
                contentPadding = PaddingValues(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                stickyHeader {
                    SectionHeader(title = "Pending (${pending.size})")
                }
                items(pending, key = { it.id }) { task -> // -- Paso 1+2: key = it.id arregla el bug de identidad --
                    TaskItem(
                        task = task,
                        onToggleDone = {
                            val index = tasks.indexOf(task)
                            if (index != -1) tasks[index] = task.copy(isDone = !task.isDone)
                        },
                        onDelete = { tasks.remove(task) }
                    )
                }

                stickyHeader {
                    SectionHeader(title = "Done (${done.size})")
                }
                items(done, key = { it.id }) { task ->
                    TaskItem(
                        task = task,
                        onToggleDone = {
                            val index = tasks.indexOf(task)
                            if (index != -1) tasks[index] = task.copy(isDone = !task.isDone)
                        },
                        onDelete = { tasks.remove(task) }
                    )
                }
            }
        }
    }
}

@Composable
private fun SectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleSmall,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface) // -- para que NO se vuelva transparente al pegarse arriba --
            .padding(vertical = 6.dp)
    )
}

@Composable
private fun EmptyTasksState(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Filled.Checklist,
            contentDescription = null,
            modifier = Modifier.size(64.dp),
            tint = Color.Gray
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text("No pending tasks. Enjoy your day!", style = MaterialTheme.typography.bodyLarge)
    }
}