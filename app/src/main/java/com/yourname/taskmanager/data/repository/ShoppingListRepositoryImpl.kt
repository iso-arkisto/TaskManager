package com.yourname.taskmanager.data.repository

import com.yourname.taskmanager.data.dao.ShoppingListDao
import com.yourname.taskmanager.data.entity.ShoppingListItem
import kotlinx.coroutines.flow.Flow

class ShoppingListRepositoryImpl(
    private val dao: ShoppingListDao
): ShoppingListRepository {
    override suspend fun insertItem(item: ShoppingListItem) {
        dao.insertItem(item)
    }

    override suspend fun deleteItem(item: ShoppingListItem) {
        dao.deleteItem(item)
    }

    override suspend fun updateItem(item: ShoppingListItem) {
        dao.updateItem(item)
    }

    override fun getAllItems(): Flow<List<ShoppingListItem>> {
        return dao.getAllItems()
    }

    override suspend fun getListItemById(listId: Int): ShoppingListItem? {
        return dao.getItemById(listId)
    }

}