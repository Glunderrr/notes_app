package files.app.notation.db

import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

interface NotesDAO {
    @Upsert
    suspend fun upsertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT*FROM note_table")
    fun getAllNotes(): Flow<List<Note>>
}