package files.app.notation

import android.app.Application
import files.app.notation.db.NotesDB

class App : Application() {
    val database by lazy { NotesDB.createDB(this) }
}