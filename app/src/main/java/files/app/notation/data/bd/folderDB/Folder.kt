package files.app.notation.data.bd.folderDB

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import files.app.notation.data.bd.folderDB.note.Note

@Entity(tableName = "folder_table")
data class Folder(
    @PrimaryKey
    val name: String,
    @TypeConverters(MutableListConverter::class)
    val notesList: MutableList<Note> = mutableListOf()
)
