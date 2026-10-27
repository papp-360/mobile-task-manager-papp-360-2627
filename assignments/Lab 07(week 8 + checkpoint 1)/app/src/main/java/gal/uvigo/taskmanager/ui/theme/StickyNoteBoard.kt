package gal.uvigo.taskmanager.ui.theme

import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.ui.unit.dp
import gal.uvigo.taskmanager.Model.Task

@Composable
fun StickyNoteBoard(tasks: List<Task>) {
    val pendingTasks = tasks.filter { !it.isDone }

    LazyVerticalGrid(
        // -- Paso 6: número de columnas AUTOMÁTICO según el ancho disponible --
        columns = GridCells.Adaptive(minSize = 160.dp),
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        // -- Header a ancho completo: ocupa todas las columnas con span --
        item(span = { GridItemSpan(maxLineSpan) }) {
            Text("My Board", style = MaterialTheme.typography.headlineSmall)
        }

        items(pendingTasks, key = { it.id }) { task ->
            TaskItem(
                task = task,
                onToggleDone = { /* no-op: este tablero es solo de prueba, sin lista observable propia */ },
                onDelete = { /* no-op por el mismo motivo */ },
                modifier = Modifier.aspectRatio(1f) // -- hace que la card sea cuadrada, como una nota adhesiva --
            )
        }
    }
}