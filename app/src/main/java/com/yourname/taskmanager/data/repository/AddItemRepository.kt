package com.yourname.taskmanager.data.repository

import com.yourname.taskmanager.data.entity.AddItem
import kotlinx.coroutines.flow.Flow

interface AddItemRepository {
    suspend fun insertItem(item: AddItem)
    suspend fun deleteItem(item: AddItem)
    suspend fun updateItem(item: AddItem)
    fun getItemsByListId(listId: Int): Flow<List<AddItem>>
    suspend fun getItemById(id: Int): AddItem?
}