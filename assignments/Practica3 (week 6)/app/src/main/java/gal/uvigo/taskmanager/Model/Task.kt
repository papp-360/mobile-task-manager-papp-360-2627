package gal.uvigo.taskmanager.Model
import java.time.LocalDate

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val dueDate: LocalDate,
    val category: Category,
    var isDone: Boolean = false
){

        val taskInfo: String
        get() = "\tID: $id \n" +
                "\tTitle: $title \n" +
                "\tDescription: $description \n" +
                "\tDeadline: ${dueDate.formatAsMonthDay()} \n" +
                "\tCategory: $category \n" +
                "\tStatus: ${if (isDone) "Finished" else "Pending"}"

    val filteredTask: String
        get() = "\tID: $id \n" + 
                "\tTitle: $title \n" +
                "\tDescription: $description \n" +
                "\tDeadline: ${dueDate.formatAsMonthDay()} \n" +
                "\tCategory: $category"
}


