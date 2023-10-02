package files.app.notation.data.bd.folderDB

import androidx.room.TypeConverter
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import files.app.notation.data.bd.folderDB.note.Note

class MutableListConverter {
    @TypeConverter
    fun fromJson(value: String): MutableList<Note> {
        val listType = object : TypeToken<MutableList<Note>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toJson(list: MutableList<Note>): String {
        return Gson().toJson(list)
    }
}