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
            }
        }
    }

}