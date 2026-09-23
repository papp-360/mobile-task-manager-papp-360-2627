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


//Metodos del Map
    fun Task.getId(): Int = this[K_ID] as Int
    fun Task.getTitle(): String = this[K_TITLE] as String
    fun Task.getIsDone(): Boolean = this[K_ISDONE] as Boolean
    fun Task.getDescription(): String = this[K_DESC] as? String
    fun Task.getDueDate(): String = this[K_DATE] as? String
    fun Task.getCategory(): String = this[K_CAT] as? String


//MutableList de Tasks
    val tasks = MutableListOf<Task>()
//funciones
    var idTask = 0

    //addTask
    fun addTask(tasks : MutableList<Task>){

        println("\n ---------------------")
        println("\t\t Add a new task")
        println("---------------------\n")

        println("Tittle: ")
        val title = readLine().orEmpty()
        println("\n Description: ")
        val description = readLine().orEmpty()
        println("\n Due date: ")
        val dueDate = readLine().orEmpty()
        println("\n Category: ")
        val category = readLine().orEmpty()

        val task = newTask(idTask, title, false, description, dueDate, category)
        tasks.add(task)
    }

    //listTasks
    fun listTasks(tasks: MutableList<Task>){
        
        val count = 1;
        println("\n ---------------------")
        println("\t\t List of tasks")
        println("---------------------\n")

        if(Tasks.isEmpty()){
            println("\n ---------------------")
            println("\t\t No tasks found")
            println("---------------------\n")
        }else{
            println("\n\ Actual tasks: \n")
            for(task in tasks){
                println("\n ---------------------")
                println("Task #" + count)
                println("ID: ${task.getId()}, Title: ${task.getTitle()}, Description: ${task.getDescription()}, Due date: ${task.getDueDate()}, Category: ${task.getCategory()}")
                println("Status: ${if(task.getIsDone()) "Done" else "Not done"}")
                println("---------------------\n")
                count++
            }

        }
    
    
    }

    //MarkTaskAsDone

    fun MarkTaskAsDone(id: Int){
        val task = tasks.find { id.getId() == id}
        if(task != null){
            task[K_ISDONE] = trrue
            println("Task: $id is now marked as Done, congrats!")
        }else{
            println("Task: $id couldn't be found, try again!")
        }

    }

    //filterTasks

    fun filterTasks(completed: Boolean){

        var count = 1
        var filtered = tasks.filter { it.isDone() == completed}

        if(filtered.isEmpty()){
                println("\nNo tasks found")
        }else{
            for(task in filtered){
                println("------------------------------")
                println("Task ${if (task.isDone()) "Finished" else "Not finished"} #" + count)
                println("------------------------------")
                count++;
            }
        }
    }

    //Interfaz del menú

    var on= true
    while(on){
        println("\n ---------------------")
        println("\t\t Task Manager")
        println("1. Add a new task: ")
        println("2. List all tasks: ")
        println("3. Mark a task as done: ")
        println("4. Filter tasks by status: ")
        println("5. Exit: ")
        println("---------------------\n")
        println("\nSelect an option: \n")

        when(readLine()?.trim()){
            "1" -> {
                addTask(tasks)
                idTask++
            }

            "2" -> {
                listTasks(tasks)
            }
            "3" -> {
                println("\n ---------------------")
                println("\t\t Enter the ID of the task to mark as done:")
                println("---------------------\n")
                val id = readLine()?.toIntOrNull()
                if(id != null){
                    MarkTaskAsDone(id)
                }else{
                    println("Invalid ID, try again!")
                }
            }
            "4" ->{
                println("\n ---------------------")
                println("\t\t Filter tasks by status:")
                println("1. Completed tasks")
                println("2. Not completed tasks")
                println("---------------------\n")
                val option = readLine()?.trim()
                when(option){
                    "1" -> filterTasks(true)
                    "2" -> filterTasks(false)
                    else -> println("Invalid option, try again!")
                }
            }
            "5" -> {
                println("Exiting the program, goodbye!")
                on = false
            }
            else -> {
                println("Invalid option, try again!")
            }
        }

    }
}