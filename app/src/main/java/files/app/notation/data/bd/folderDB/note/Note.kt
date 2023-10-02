package files.app.notation.data.bd.folderDB.note

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    val title: String,
    val content: String = "",
    val date: String = CurrentDate.getCurrentDateTime(),
)