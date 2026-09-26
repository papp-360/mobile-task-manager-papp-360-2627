import java.time.LocalDate

fun main() {

    //controlador de tareas 
    val controller = TaskController()

    //Menu CLI
    var on = true

    while(on){
                println("\n---------------------------------")
        println("*" + "\t\t\t\t*") 
        println("*\t  |TASK MANAGER|" + "\t*")
        println("*" + "\t\t\t\t*") 
        println("*\t1. Add Task" + "\t\t*")
        println("*\t2. List All" + "\t\t*")
        println("*\t3. Mark Task as Done" + "\t*")
        println("*\t4. Filter Tasks" + "\t\t*")
        println("*\t5. Exit" + "\t\t\t*")
        println("*" + "\t\t\t\t*") 
        println("-------------------------\n")
        println("Choose Option:")

        when(readLine()?.trim()){

            "1" -> {
                prinln("\n--------------------------")
                println("\tEnter new Task:")
                println("--------------------------\n")

                //introduccion de datos
                //Titulo requerido
                val tittle: String = run{
                    val str: String
                    do{
                        println("\nEnter Task Title:")
                        str = readLine()?.trim().orEmpty() 
                        if(str.isBlank()) println("Title cannot be empty.")

                    }while(str.isBlank())
                    str
                }
                println("\nDescription (Optional):")
                val description: String = readLine()?.trim().orEmpty()

                //Fecha requerida
                val dueDate: LocalDate = run{
                    var date: LocalDate? = null
                    do{
                        println("\nEnter Due Date (YYYY-MM-DD):")
                        val inputDate = readLine()?.trim().orEmpty()
                        date = if(inputDate.isBlank()){
                            LocalDate.now()
                        } else {
                            try{
                                LocalDate.parse(inputDate)
                            } catch(e: Exception){
                                println("Invalid date format. Please use YYYY-MM-DD.")
                                null
                        }
                    }
                }while(date == null)
                date
            }
            //Categoria
            println("\nCategory (${Category.values().joinToString{it.name}}): ")
            val categoryInput = readLine().orEmpty().uppercase()
            val Category =  Category.values().firstOrNull { it.name == categoryInput }?: Category.OTHERS

            controller.addTask(tittle, description, dueDate, Category)
            println("\nTask added successfully!")
        }

        "2" -> {
            println("\n--------------------------")
            println("\tList of Tasks:")
            println("--------------------------\n")
            
            printn("\nActual Tasks:")
            println("--------------------------")
            controller.getAllTasks()
            println("--------------------------\n")
        }

        "3" -> {
            println("\n--------------------------")
            println("\tSelect Task ID")
            println("--------------------------\n")

            val id = readLine()?.toIntOrNull()
            
            if(id != null){
                var status = controller.markTaskAsDone(id)
                if(status) println("\nTask $id marked as done.")
                else println("\nTask $id not found or already done.")

            }else{
                println("Invalid ID. Please enter a valid number.")
            }
        }
        "4" ->{
            println("\n--------------------------")
            println("\tFilter Tasks:")
            println("--------------------------\n")

            println("\nFilter by: \n")
            println("1. Finished")
            println("2. Pending")

            when(readLine()?.trim()){
                "1" -> {
                    println("\nFinished Tasks:")
                    println("--------------------------")
                    controller.filterTasks(true)
                    println("--------------------------\n")
                }
                "2" -> {
                    println("\nPending Tasks:")
                    println("--------------------------")
                    controller.filterTasks(false)
                    println("--------------------------\n")
                }
                else -> {
                    println("Invalid option. Please choose 1 or 2.")
                }
            }
        }

        "5" -> on = false
        else -> {
            println("Invalid option. Please choose a number between 1 and 5.")
            }
        }

    }
}
