package com.yourname.taskmanager.screen.note_list_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yourname.taskmanager.data.entity.NoteItem
import com.yourname.taskmanager.data.repository.NoteItemRepository
import com.yourname.taskmanager.dialog.DialogController
import com.yourname.taskmanager.dialog.DialogEvent
import com.yourname.taskmanager.utils.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val repository: NoteItemRepository,

): ViewModel(), DialogController {
    override var dialogTitle = mutableStateOf("Delete this note?")
        private set

    override var editableText = mutableStateOf("")
        private set

    override var openDialog = mutableStateOf(false)
        private set

    override var showEditableText = mutableStateOf(false)
        private set

    private var noteItem: NoteItem? = null

    var noteList by mutableStateOf(listOf<NoteItem>())

    var searchQuery by mutableStateOf("")
        private set

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        updateNoteList()
    }

    fun updateNoteList(
        searchQuery: String? = null
    ) {
        viewModelScope.launch {
            repository.getAllItems().collect { items ->
                noteList = if(searchQuery.isNullOrBlank()) {
                    items
                } else {
                    items.filter {
                        it.title.contains(searchQuery, ignoreCase = true) ||
                                it.description.contains(searchQuery, ignoreCase = true)
                    }
                }
            }
        }
    }

    fun onEvent(event: NoteListEvent) {
        when(event) {
            is NoteListEvent.OnShowDeleteDialog -> {
                openDialog.value = true
                noteItem = event.item
            }
            is NoteListEvent.OnTextSearchChange -> {
                searchQuery = event.text
                updateNoteList(searchQuery)
            }
            is NoteListEvent.OnCancelItem -> {
                viewModelScope.launch {
                    repository.insertItem(noteItem!!)
                }
            }
            is NoteListEvent.OnItemClick -> {
                sendUiEvent(UIEvent.Navigate(event.route))
            }
        }
    }

    private fun sendUiEvent(event: UIEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

    override fun onDialogEvent(event: DialogEvent) {
        when(event) {
            is DialogEvent.OnConfirm -> {
                viewModelScope.launch {
                    repository.deleteItem(noteItem!!)
                    sendUiEvent(UIEvent.ShowSnackBar("Item deleted"))
                }
                updateNoteList()
                openDialog.value = false
            }
            is DialogEvent.OnCancel -> {
                openDialog.value = false
            }
            else -> {}
        }
    }
}