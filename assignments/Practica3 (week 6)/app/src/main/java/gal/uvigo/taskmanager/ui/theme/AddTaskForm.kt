package gal.uvigo.taskmanager.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import gal.uvigo.taskmanager.Model.Category
import gal.uvigo.taskmanager.Model.Task
import java.time.LocalDate
import androidx.compose.foundation.clickable

@Composable
fun AddTaskForm(onAddTask: (Task) -> Unit) {
    // -- Paso 5 adelantado: rememberSaveable para que el borrador sobreviva a rotación --
    var titleInput by rememberSaveable { mutableStateOf("") }
    var selectedCategory by rememberSaveable { mutableStateOf(Category.OTHER) }
    var showError by rememberSaveable { mutableStateOf(false) }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {

        // -- Text Input: controlled component --
        OutlinedTextField(
            value = titleInput,
            onValueChange = {
                titleInput = it
                if (showError && it.isNotBlank()) showError = false
            },
            label = { Text("Task title") },
            isError = showError,
            supportingText = {
                if (showError) Text("Title cannot be empty")
            },
            modifier = Modifier.fillMaxWidth()
        )

        // -- Category Picker: Row de "chips" seleccionables --
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Category.values().forEach { category ->
                val isSelected = category == selectedCategory
                Surface(
                    color = if (isSelected) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.clip(RoundedCornerShape(16.dp))
                ) {
                    Text(
                        text = category.name,
                        color = if (isSelected) Color.White else Color.Black,
                        modifier = Modifier
                            .clickable { selectedCategory = category }
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    )
                }
            }
        }

        // -- Submission --
        Button(
            onClick = {
                if (titleInput.isBlank()) {
                    showError = true
                } else {
                    onAddTask(
                        Task(
                            id = (0..100000).random(), // -- simple para este lab; en Lab 06+ será un id real --
                            title = titleInput,
                            description = "",
                            dueDate = LocalDate.now(),
                            category = selectedCategory
                        )
                    )
                    // -- limpiar formulario tras éxito --
                    titleInput = ""
                    selectedCategory = Category.OTHER
                    showError = false
                }
            },
            enabled = titleInput.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Task")
        }
    }
}