package gal.uvigo.taskmanager.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import gal.uvigo.taskmanager.Model.Category

@Composable
fun CategoryFilterRow(selected: Category?, onSelect: (Category?) -> Unit) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        FilterChip(label = "All", isSelected = selected == null, onClick = { onSelect(null) })
        Category.values().forEach { category ->
            FilterChip(
                label = category.name,
                isSelected = selected == category,
                onClick = { onSelect(category) }
            )
        }
    }
}

@Composable
private fun FilterChip(label: String, isSelected: Boolean, onClick: () -> Unit) {
    Surface(
        color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.clip(RoundedCornerShape(16.dp))
    ) {
        Text(
            text = label,
            color = if (isSelected) Color.White else Color.Black,
            modifier = Modifier.clickable { onClick() }.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}
