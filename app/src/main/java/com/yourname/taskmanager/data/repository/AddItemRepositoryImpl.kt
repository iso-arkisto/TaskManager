package com.yourname.taskmanager.data.repository

import com.yourname.taskmanager.data.dao.AddItemDao
import com.yourname.taskmanager.data.entity.AddItem
import kotlinx.coroutines.flow.Flow

class AddItemRepositoryImpl(
    private val dao: AddItemDao
): AddItemRepository {
    override suspend fun insertItem(item: AddItem) {
        dao.insertItem(item)
    }

    override suspend fun deleteItem(item: AddItem) {
        dao.deleteItem(item)
    }

    override suspend fun updateItem(item: AddItem) {
       dao.updateItem(item)
    }

    override fun getItemsByListId(listId: Int): Flow<List<AddItem>> {
        return dao.getAllItemsById(listId)
    }

    override suspend fun getItemById(id: Int): AddItem? {
        return dao.getItemById(id)
    }

}