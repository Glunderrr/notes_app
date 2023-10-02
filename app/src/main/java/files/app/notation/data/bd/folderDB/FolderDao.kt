package files.app.notation.data.bd.folderDB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.TypeConverters
import androidx.room.Upsert
import files.app.notation.data.bd.folderDB.note.Note
import kotlinx.coroutines.flow.Flow


@Dao
interface FolderDao {
    @Upsert
    suspend fun upsertFolder(folder: Folder)

    @Delete
    suspend fun deleteFolder(folder: Folder)

    @Query("SELECT*FROM folder_table")
    fun getAllFolders(): Flow<List<Folder>>

/*    @TypeConverters(MutableListConverter::class)
    @Query("SELECT notesMutableList FROM folder_table")
    fun getAllNotes():Flow<List<MutableList<Note>>>*/
}