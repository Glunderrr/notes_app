package files.app.notation.data.bd.folderDB.note

import java.util.Calendar

data class Note(
    val title: String,
    val content: String = "",
    val date: String = getCurrentDateTime(),
)
private fun getCurrentDateTime(): String {
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH) + 1
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val minute = calendar.get(Calendar.MINUTE)
    val second = calendar.get(Calendar.SECOND)
    return String.format("%04d-%02d-%02d %02d:%02d:%02d", year, month, day, hour, minute, second)
}