import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun LocalDate.formatasMonthDay(): String {
    val formatter = DateTimeFormatter.ofPattern("MM dd")
    return this.format(formatter)
}