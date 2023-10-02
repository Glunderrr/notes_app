package files.app.notation.data.bd.folderDB

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import files.app.notation.data.bd.folderDB.FolderDao
import files.app.notation.data.bd.folderDB.note.Note


@Database(entities = [Folder::class,], version = 1)
@TypeConverters(MutableListConverter::class)
abstract class DB : RoomDatabase() {
    abstract val folderDao: FolderDao
}