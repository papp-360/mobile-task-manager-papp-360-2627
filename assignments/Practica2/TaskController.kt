import java.time.LocalDate

class TaskController {
     private val tasks = mutableListOf<Task>()
     private var nextId = 1

     fun addTask(
        title: String,
        description: String,
        dueDate: LocalDate,
        category: Category) {
         val task = Task(nextId, title, description, dueDate, category)
         tasks.add(task)
         this.nextId++
         println("Task added successfully!")
     }

     fun markTaskAsDone(id: Int): Boolean {
         val task = tasks.find { it.id == id }
         if (task != null && !task.isDone){
            val indice = tasks.indexOf(task)
            tasks[indice] = task.copy(isDone = true)
            done = true
         }else {
            done = false
         }
         return done
     }

     fun getAllTasks(){
        var count = 1
        if(tasks.isEmpty()){
            println("No tasks available.")
     }else{
            for(task in tasks){
                println("\nTask $count:")
                println("--------------------------")
                println(task.taskInfo)
                println("--------------------------\n")
                count++
            }
        }
     }

     fun filterTasks(completed: Boolean){
        var count = 1
        val filteredTasks = tasks.filter { it.isDone == completed }
        if(filteredTasks.isEmpty()){
            println("No tasks found for the selected filter.")
        }else{
            for(task in filteredTasks){
                println("\nTask $count:")
                println("--------------------------")
                println(task.filteredTask)
                println("--------------------------\n")
                count++
            }
        }
     }
}