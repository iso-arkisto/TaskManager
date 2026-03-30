package com.yourname.taskmanager.screen.new_note_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yourname.taskmanager.data.entity.NoteItem
import com.yourname.taskmanager.data.repository.NoteItemRepository
import com.yourname.taskmanager.datastore.DatastoreManager
import com.yourname.taskmanager.utils.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewNoteViewModel @Inject constructor(
    private val repository: NoteItemRepository,
    private val dataStore: DatastoreManager,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {
    private var noteId = -1
    private var noteItem: NoteItem? = null

    var titleColor = mutableStateOf("#FFB388FF")
    var title by mutableStateOf("")
        private set
    var description by mutableStateOf("")
        private set

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val noteIdString = savedStateHandle.get<String>("noteId")
        noteId = noteIdString?.toIntOrNull() ?: -1

        if(noteId > 0) {
            viewModelScope.launch {
                 repository.getNoteItemById(noteId).let { item ->
                    title = item.title
                    description = item.description
                    this@NewNoteViewModel.noteItem = item
                }

                dataStore.getStringPreference(
                    DatastoreManager.TITLE_COLOR,
                    "#FFB388FF"
                ).collect { color ->
                    titleColor.value = color
                }

            }
        }
    }

    fun onEvent(event: NewNoteEvent) {
        when(event) {
            is NewNoteEvent.OnSave -> {
                viewModelScope.launch {
                    if(title.isBlank()) {
                        sendUiEvent(UIEvent.ShowSnackBar("Title cannot be empty"))
                        return@launch
                    }

                    repository.insertItem(NoteItem(
                        title = title,
                        description = description,
                        time = System.currentTimeMillis().toString(),
                        id = noteId
                    ))

                    sendUiEvent(UIEvent.PopBackStack)
                }
            }
            is NewNoteEvent.OnDescriptionChange -> {
                description = event.description
            }
            is NewNoteEvent.OnTitleChange -> {
                title = event.title
            }
        }
    }

    private fun sendUiEvent(event: UIEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }


}