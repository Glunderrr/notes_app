package files.app.notation.repository

import files.app.notation.data.bd.folderDB.Folder
import files.app.notation.data.bd.folderDB.FolderDao
import javax.inject.Inject

class Repository @Inject constructor(
    private val folderDao: FolderDao
) {
    suspend fun deleteFolders(folder: Folder) {
        folderDao.deleteFolder(folder)
    }

    suspend fun upsertFolder(folder: Folder) {
        folderDao.upsertFolder(folder)
    }

    fun getAllFolders() = folderDao.getAllFolders()
}