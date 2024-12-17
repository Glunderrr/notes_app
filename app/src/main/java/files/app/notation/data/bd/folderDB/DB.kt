package files.app.notation.data.bd.folderDB

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [Folder::class], version = 3)
@TypeConverters(MutableListConverter::class)
abstract class DB : RoomDatabase() {
    abstract val folderDao: FolderDao
}