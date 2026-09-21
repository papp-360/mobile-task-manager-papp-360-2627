//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or

const val K_ID = "id"
const val K_TITLE = "title"
const val K_ISDONE = "isDone"
const val K_DESC = "description"
const val K_DATE = "dueDate"
const val K_CAT = "category"
//creamos el objeto task
typealias Task = Map<String, Any>

// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    //funcion para crear una nueva tarea
    fun newTask(id: Int, title: String, isDone: Boolean, description: String, dueDate: String, category: String): Task = mutableMapOf(
            K_ID to id,
            K_TITLE to title,
            K_ISDONE to isDone,
            K_DESC to description,
            K_DATE to dueDate,
            K_CAT to category
        )









        
    
}