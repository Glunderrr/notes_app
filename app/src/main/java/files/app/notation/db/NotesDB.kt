package files.app.notation.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [Note::class], version = 1
)
abstract class NotesDB : RoomDatabase() {
    abstract val dao: NotesDAO

    companion object {
        fun createDB(context: Context): NotesDB {
            return Room.databaseBuilder(
                context = context,
                NotesDB::class.java,
                "notes"
            ).build()
        }
    }
}