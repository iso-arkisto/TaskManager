package com.yourname.taskmanager.screen.shopping_list_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yourname.taskmanager.data.entity.ShoppingListItem
import com.yourname.taskmanager.data.repository.ShoppingListRepository
import com.yourname.taskmanager.dialog.DialogController
import com.yourname.taskmanager.dialog.DialogEvent
import com.yourname.taskmanager.utils.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingListViewModel @Inject constructor(
    private val repository: ShoppingListRepository
): ViewModel(), DialogController {

    val list = repository.getAllItems()
    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var listItem: ShoppingListItem? = null

    override var dialogTitle = mutableStateOf("List name")
        private set

    override var editableText = mutableStateOf("")
        private set
    override var openDialog = mutableStateOf(false)
        private set
    override var showEditableText = mutableStateOf(true)
        private set

    fun onEvent(event: ShoppingListEvent) {
        when(event) {
            is ShoppingListEvent.OnItemSave -> {
                if(editableText.value.isBlank()) return
                viewModelScope.launch {
                    repository.insertItem(
                        ShoppingListItem(
                            id = listItem?.id,
                            name = editableText.value,
                            allItemsCount = listItem?.allItemsCount ?: 0,
                            allSelectedItemsCount = listItem?.allSelectedItemsCount ?: 0,
                            createdAt = listItem?.createdAt ?: System.currentTimeMillis()
                        )
                    )
                }
            }
            is ShoppingListEvent.OnItemClick -> {
                sendUiEvent(UIEvent.Navigate(
                    route = event.route
                ))
            }

            is ShoppingListEvent.OnShowDeleteDialog -> {
                listItem = event.item
                openDialog.value = true
                dialogTitle.value = "Удалить запись?"
                showEditableText.value = false
            }

            is ShoppingListEvent.OnShowEditDialog -> {
                listItem = event.item
                openDialog.value = true
                dialogTitle.value = "Название записи:"
                showEditableText.value = true
                editableText.value = listItem?.name ?: ""
            }
        }
    }
    override fun onDialogEvent(event: DialogEvent) {
        when(event) {
            is DialogEvent.OnCancel -> {
                openDialog.value = false
            }
            is DialogEvent.OnConfirm -> {
                if(showEditableText.value) {
                    onEvent(ShoppingListEvent.OnItemSave)
                } else {
                    viewModelScope.launch {
                        listItem?.let {
                            item ->
                            repository.deleteItem(item)
                        }
                    }
                }
                openDialog.value = false
            }
            is DialogEvent.OnTextChange -> {
                editableText.value = event.text
            }
        }
    }

    private fun sendUiEvent(event: UIEvent.Navigate) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

}