// -- TaskViewModel.kt --
package gal.uvigo.taskmanager.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import gal.uvigo.taskmanager.Model.Category
import gal.uvigo.taskmanager.Model.Task
import java.time.LocalDate

class TaskViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    // -- Paso 2: lista cruda, PRIVADA. La UI no puede mutarla directamente --
    private val _tasks = mutableStateListOf(
        Task(1, "Buy groceries", "", LocalDate.now().minusDays(2), Category.PERSONAL),
        Task(2, "Finish Kotlin lab", "", LocalDate.now(), Category.WORK, isDone = true),
        Task(3, "Team meeting", "", LocalDate.now().plusDays(3), Category.WORK)
    )

    // -- Paso 5: filtro persistente con SavedStateHandle (detalle abajo) --
    @OptIn(SavedStateHandleSaveableApi::class)
    var selectedCategoryFilter: Category? by savedStateHandle.saveable { mutableStateOf(null) }
        private set

    // -- Paso 3: datos YA agrupados y filtrados. La UI solo pinta, no decide --
    val pendingTasks: List<Task>
        get() = _tasks.filter { !it.isDone && matchesFilter(it) }

    val doneTasks: List<Task>
        get() = _tasks.filter { it.isDone && matchesFilter(it) }

    private fun matchesFilter(task: Task): Boolean =
        selectedCategoryFilter == null || task.category == selectedCategoryFilter

    // -- Eventos / lógica de negocio (intents del usuario) --
    fun addTask(task: Task) {
        _tasks.add(task)
    }

    fun toggleTaskDone(taskId: Int) {
        val index = _tasks.indexOfFirst { it.id == taskId }
        if (index != -1) {
            _tasks[index] = _tasks[index].copy(isDone = !_tasks[index].isDone)
        }
    }

    fun deleteTask(taskId: Int) {
        _tasks.removeAll { it.id == taskId }
    }

    fun setCategoryFilter(category: Category?) {
        selectedCategoryFilter = category
    }
}