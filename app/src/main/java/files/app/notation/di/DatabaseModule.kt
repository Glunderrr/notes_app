package files.app.notation.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import files.app.notation.data.bd.folderDB.DB
import files.app.notation.data.bd.folderDB.Folder
import files.app.notation.data.bd.folderDB.FolderDao
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @OptIn(DelicateCoroutinesApi::class)
    @Provides
    @Singleton
    fun provideDB(@ApplicationContext context: Context): DB {
        return Room.databaseBuilder(
            context = context,
            DB::class.java,
            "folders_db"
        ).build().apply {
            GlobalScope.launch {
                folderDao.upsertFolder(Folder(name = "General folder"))
            }
        }.also {
            Log.i("MY_DB", "create db: $it")
        }
    }

    @Provides
    fun provideMyDao(database: DB): FolderDao {
        return database.folderDao
    }
}