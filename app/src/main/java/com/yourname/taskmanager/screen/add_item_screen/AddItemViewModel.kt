package com.yourname.taskmanager.screen.add_item_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yourname.taskmanager.data.entity.AddItem
import com.yourname.taskmanager.data.entity.ShoppingListItem
import com.yourname.taskmanager.data.repository.AddItemRepository
import com.yourname.taskmanager.data.repository.ShoppingListRepository
import com.yourname.taskmanager.dialog.DialogController
import com.yourname.taskmanager.dialog.DialogEvent
import com.yourname.taskmanager.utils.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddItemViewModel @Inject constructor(
    private val repository: AddItemRepository,
    private val shoppingListRepository: ShoppingListRepository,
    savedStateHandle: SavedStateHandle
): ViewModel(), DialogController {

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    var itemsList: Flow<List<AddItem>>? = null
    var addItem: AddItem? = null
    var shoppingListItem: ShoppingListItem? = null
    var listId: Int = -1

    init {
        listId = savedStateHandle.get<String>("listId")?.toInt()!!
        itemsList = repository.getItemsByListId(listId)
        viewModelScope.launch {
            shoppingListItem = shoppingListRepository.getListItemById(listId)
        }
    }

    var itemText = mutableStateOf("")
        private set

    override var dialogTitle = mutableStateOf("Edit name:")
        private set
    override var editableText = mutableStateOf("")
        private set
    override var openDialog = mutableStateOf(false)
        private set
    override var showEditableText = mutableStateOf(true)
        private set

    fun onEvent(event: AddItemEvent){
        when(event){
            is AddItemEvent.OnItemSave -> {
                viewModelScope.launch {
                    if (listId == -1) return@launch
                    if (addItem != null){
                        if(addItem!!.name.isEmpty()){
                            sendUiEvent(UIEvent.ShowSnackBar("Name must not be empty!"))
                            return@launch
                        }
                    } else {
                        if(itemText.value.isEmpty()){
                            sendUiEvent(UIEvent.ShowSnackBar("Name must not be empty!"))
                            return@launch
                        }
                    }
                    repository.insertItem(
                        AddItem(
                            id = addItem?.id,
                            name = addItem?.name ?: itemText.value,
                            isChecked = addItem?.isChecked ?: false,
                            listId = listId
                        )
                    )
                    itemText.value = ""
                    addItem = null
                }
                viewModelScope.launch {
                    updateShoppingListCount(false)
                }
            }
            is AddItemEvent.OnShowEditDialog -> {
                addItem = event.item
                openDialog.value = true
                editableText.value = addItem?.name ?: ""
            }
            is AddItemEvent.OnTextChange -> {
                itemText.value = event.text
            }
            is AddItemEvent.OnDelete -> {
                viewModelScope.launch {
                    repository.deleteItem(event.item)
                }
                viewModelScope.launch {
                    updateShoppingListCount(false)
                }
            }
            is AddItemEvent.OnCheckedChange -> {
                viewModelScope.launch {
                    repository.updateItem(event.item)
                }
                viewModelScope.launch {
                    updateShoppingListCount(true)
                }
            }
        }
    }

    override fun onDialogEvent(event: DialogEvent) {
        when(event){
            is DialogEvent.OnCancel -> {
                openDialog.value = false
                editableText.value = ""
            }
            DialogEvent.OnConfirm -> {
                openDialog.value = false
                addItem = addItem?.copy(name = editableText.value)
                editableText.value = ""
                onEvent(AddItemEvent.OnItemSave)
            }
            is DialogEvent.OnTextChange -> {
                editableText.value = event.text
            }
        }
    }

    private suspend fun updateShoppingListCount(isCheckChanged: Boolean = false){
        itemsList?.let { flow ->
            val list = flow.first()
            var counter = 0
            list.forEach { item ->
                if(item.isChecked) counter++
            }

            val updatedItem = shoppingListItem?.copy(
                allItemsCount = list.size,
                allSelectedItemsCount = counter
            )
            updatedItem?.let { shItem ->
                shoppingListRepository.updateItem(shItem)
                shoppingListItem = shItem
            }
        }
    }
    private fun sendUiEvent(event: UIEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

}