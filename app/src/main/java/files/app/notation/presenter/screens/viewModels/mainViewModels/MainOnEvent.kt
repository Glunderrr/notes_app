package files.app.notation.presenter.screens.viewModels.mainViewModels

import files.app.notation.data.bd.folderDB.Folder
import files.app.notation.data.bd.folderDB.note.Note

sealed class MainOnEvent {
    object OpenAddNoteDialog : MainOnEvent()
    object CloseAddNoteDialog: MainOnEvent()
    object OpenSearchScreen : MainOnEvent()
    object OpenSettings : MainOnEvent()
    data class AddNote(val folder: Folder, val note: Note) : MainOnEvent()
    data class OpenNote(val note: Note): MainOnEvent()
    data class CreateFolder(val folderName:String) :MainOnEvent()
}
