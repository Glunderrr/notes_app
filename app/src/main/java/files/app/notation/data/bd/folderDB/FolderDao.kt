package files.app.notation.data.bd.folderDB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow


@Dao
interface FolderDao {
    @Upsert
    suspend fun upsertFolder(folder: Folder)

    @Delete
    suspend fun deleteFolder(folder: Folder)

    @Query("SELECT*FROM folder_table")
    fun getAllFolders(): Flow<List<Folder>>
}