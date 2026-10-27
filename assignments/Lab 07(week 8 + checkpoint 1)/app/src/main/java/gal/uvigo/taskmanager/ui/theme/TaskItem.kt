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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import gal.uvigo.taskmanager.Model.Task
import java.time.LocalDate
import kotlin.random.Random

@Composable
fun TaskItem(
    task: Task,
    onToggleDone: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isOverdue = task.dueDate.isBefore(LocalDate.now()) && !task.isDone

    // -- Paso 2: color aleatorio, memorizado SOLO para esta instancia del composable --
    val cardColor = remember {
        Color(
            red = Random.nextInt(180, 256),
            green = Random.nextInt(180, 256),
            blue = Random.nextInt(180, 256)
        )
    }

    Card(
        // -- Paso 3: SIN padding vertical aquí; ese espaciado ahora lo gestiona el LazyColumn --
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = cardColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), // -- esto se queda: es "breathing room" DENTRO de la card, no espaciado ENTRE cards --
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(task.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .background(Color(0x33000000), RoundedCornerShape(6.dp))
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Text(task.category.name, style = MaterialTheme.typography.labelSmall)
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
                        task.dueDate.toString(),
                        style = MaterialTheme.typography.bodySmall,
                        color = if (isOverdue) Color.Red else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Icon(
                imageVector = if (task.isDone) Icons.Filled.CheckCircle else Icons.Filled.RadioButtonUnchecked,
                contentDescription = if (task.isDone) "Done" else "Pending",
                tint = if (task.isDone) Color(0xFF2E7D32) else Color.DarkGray,
                modifier = Modifier.size(28.dp).clickable { onToggleDone() }
            )

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(onClick = onDelete) {
                Icon(Icons.Filled.Delete, contentDescription = "Delete task", tint = Color.DarkGray)
            }
        }
    }
}