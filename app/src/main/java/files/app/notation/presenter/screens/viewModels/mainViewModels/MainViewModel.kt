package files.app.notation.presenter.screens.viewModels.mainViewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import files.app.notation.repository.Repository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val folders = repository.getAllFolders()
/*    val notes =repository.getAllNotes()*/

    val showAddNoteDialog: Boolean
        get() = _showAddNoteDialog

    private var _showAddNoteDialog by mutableStateOf(false)
    val showSettingsState = mutableStateOf(false)

    fun onEvent(event: MainOnEvent) {
        when (event) {
            is MainOnEvent.AddNote -> viewModelScope.launch {
                event.folder.notesMutableList.add(event.note).also {
                    Log.i(
                        "ADD_NOTE_TO_FOLDER",
                        "note:${event.note}; folder:${event.folder}"
                    )
                }
                repository.upsertFolder(event.folder).also {
                    Log.i(
                        "UPDATE_FOLDER_BY_NOTE",
                        "note:${event.note}; folder:${event.folder}"
                    )
                }
            }

            MainOnEvent.OpenAddNoteDialog -> _showAddNoteDialog = true
            MainOnEvent.CloseAddNoteDialog -> _showAddNoteDialog = false
            MainOnEvent.OpenSettings -> showSettingsState.value = true
            MainOnEvent.OpenSearchScreen -> {}
            is MainOnEvent.OpenNote -> {}
            is MainOnEvent.CreateFolder -> {}
        }
    }
}