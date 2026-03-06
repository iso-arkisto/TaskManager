package com.yourname.taskmanager.screen.main_screen

import android.app.Dialog
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yourname.taskmanager.data.entity.ShoppingListItem
import com.yourname.taskmanager.data.repository.ShoppingListRepository
import com.yourname.taskmanager.dialog.DialogController
import com.yourname.taskmanager.dialog.DialogEvent
import com.yourname.taskmanager.utils.Routes
import com.yourname.taskmanager.utils.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: ShoppingListRepository,
): ViewModel(), DialogController {
    override var dialogTitle = mutableStateOf("List name")
        private set

    override var editableText = mutableStateOf("")
        private set

    override var openDialog = mutableStateOf(false)
        private set

    override var showEditableText = mutableStateOf(true)
        private set

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    var showFloatingButton = mutableStateOf(true)
        private set

    fun onEvent(event: MainScreenEvent) {
        when(event) {
            is MainScreenEvent.OnItemSave -> {
                if(editableText.value.isBlank()) return
                viewModelScope.launch {
                    repository.insertItem(ShoppingListItem(
                        id = null,
                        name = editableText.value,
                        time = System.currentTimeMillis().toString(),
                        allItemsCount = 0,
                        allSelectedItemsCount = 0
                    ))
                }
            }
            is MainScreenEvent.OnShowEditDialog -> {
                editableText.value = ""
                openDialog.value = true
            }
            is MainScreenEvent.Navigate -> {
                sendUiEvent(UIEvent.Navigate(event.route))
            }
            is MainScreenEvent.NavigateMain -> {
                sendUiEvent(UIEvent.NavigateMain(event.route))
            }
        }
    }

    fun updateFloatingButtonVisibility(
        route: String
    ) {
        showFloatingButton.value = !(route == Routes.ABOUT || route == Routes.SETTINGS)
    }

    override fun onDialogEvent(event: DialogEvent) {
        when(event) {
            is DialogEvent.OnCancel -> {
                openDialog.value = false
                editableText.value = ""
            }
            is DialogEvent.OnConfirm -> {
                onEvent(MainScreenEvent.OnItemSave)
                openDialog.value = false
                editableText.value = ""
            }
            is DialogEvent.OnTextChange -> {
                editableText.value = event.text
            }
        }
    }

    private fun sendUiEvent(uiEvent: UIEvent) {
        viewModelScope.launch {
            _uiEvent.send(uiEvent)
        }
    }
}