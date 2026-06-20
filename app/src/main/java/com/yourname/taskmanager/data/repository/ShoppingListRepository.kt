package com.yourname.taskmanager.data.repository

import com.yourname.taskmanager.data.entity.ShoppingListItem
import kotlinx.coroutines.flow.Flow

interface ShoppingListRepository {
    suspend fun insertItem(item: ShoppingListItem)
    suspend fun deleteItem(item: ShoppingListItem)
    suspend fun updateItem(item: ShoppingListItem)
    fun getAllItems(): Flow<List<ShoppingListItem>>
    suspend fun getListItemById(listId: Int): ShoppingListItem?
}