package gal.uvigo.taskmanager.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import gal.uvigo.taskmanager.R
import gal.uvigo.taskmanager.viewmodel.TaskViewModel
import gal.uvigo.taskmanager.ui.theme.TaskItem
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(viewModel: TaskViewModel = viewModel()) { // -- Paso 4: factory function --

    Column(modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)) {

        Spacer(modifier = Modifier.height(16.dp))
        Text(stringResource(id = R.string.title_welcome), style = MaterialTheme.typography.headlineSmall)

        val totalCount = viewModel.pendingTasks.size + viewModel.doneTasks.size
        Text("Completed: ${viewModel.doneTasks.size} of $totalCount tasks", style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(8.dp))

        AddTaskForm(onAddTask = { newTask -> viewModel.addTask(newTask) })

        Spacer(modifier = Modifier.height(8.dp))

        // -- Paso 5: fila de filtro por categoría --
        CategoryFilterRow(
            selected = viewModel.selectedCategoryFilter,
            onSelect = { viewModel.setCategoryFilter(it) }
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (totalCount == 0) {
            EmptyTasksState(modifier = Modifier.weight(1f))
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f).fillMaxWidth(),
                contentPadding = PaddingValues(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                stickyHeader { SectionHeader("Pending (${viewModel.pendingTasks.size})") }
                items(viewModel.pendingTasks, key = { it.id }) { task ->
                    TaskItem(
                        task = task,
                        onToggleDone = { viewModel.toggleTaskDone(task.id) },
                        onDelete = { viewModel.deleteTask(task.id) }
                    )
                }

                stickyHeader { SectionHeader("Done (${viewModel.doneTasks.size})") }
                items(viewModel.doneTasks, key = { it.id }) { task ->
                    TaskItem(
                        task = task,
                        onToggleDone = { viewModel.toggleTaskDone(task.id) },
                        onDelete = { viewModel.deleteTask(task.id) }
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
        modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surface).padding(vertical = 6.dp)
    )
}

@Composable
private fun EmptyTasksState(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(Icons.Filled.Checklist, contentDescription = null, modifier = Modifier.size(64.dp), tint = Color.Gray)
        Spacer(modifier = Modifier.height(8.dp))
        Text("No pending tasks. Enjoy your day!", style = MaterialTheme.typography.bodyLarge)
    }
}