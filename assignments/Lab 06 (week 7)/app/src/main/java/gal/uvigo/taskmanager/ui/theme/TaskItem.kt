package gal.uvigo.taskmanager.ui.theme
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import gal.uvigo.taskmanager.Model.Task
import java.time.LocalDate

@Composable
fun TaskItem(
    task: Task,
    modifier: Modifier = Modifier,
    onToggleDone: () -> Unit, //evento : "el usuario pulsó el boton de estado"
    onDelete: () -> Unit //evento : "el usuario pulsó borrar"

    ) {
    val isOverdue = task.dueDate.isBefore(LocalDate.now()) && !task.isDone

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Box(
                    modifier = Modifier
                        .background(
                            color = Color(0xFFE0E0E0),
                            shape = RoundedCornerShape(6.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = task.category.name,
                        style = MaterialTheme.typography.labelSmall
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.CalendarToday,
                        contentDescription = "Due date",
                        modifier = Modifier.size(14.dp),
                        tint = if (isOverdue) Color.Red else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = task.dueDate.toString(),
                        style = MaterialTheme.typography.bodySmall,
                        color = if (isOverdue) Color.Red else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            //AHORA ES CLICKABLE Y LANZA EL EVENTO HACIA EL PADRE
            Icon(
                imageVector = if (task.isDone) Icons.Filled.CheckCircle else Icons.Filled.RadioButtonUnchecked,
                contentDescription = if (task.isDone) "Done" else "Pending",
                tint = if (task.isDone) Color(0xFF4CAF50) else Color.Gray,
                modifier = Modifier.size(28.dp).clickable { onToggleDone() }
            )
            Spacer(modifier = Modifier.width(8.dp))

            //Delete button
            IconButton(onClick = onDelete) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Delete  task",
                    tint = Color.Gray
                )
            }
        }
    }
}