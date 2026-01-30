package com.yourname.taskmanager.data.repository

import com.yourname.taskmanager.data.dao.NoteItemDao
import com.yourname.taskmanager.data.entity.NoteItem
import kotlinx.coroutines.flow.Flow

class NoteItemRepositoryImpl(
    private val dao: NoteItemDao
): NoteItemRepository {
    override suspend fun insertItem(item: NoteItem) {
        dao.insertItem(item)
    }

    override suspend fun deleteItem(item: NoteItem) {
        dao.deleteItem(item)
    }

    override fun getAllItems(): Flow<List<NoteItem>> {
        return dao.getAllItems()
    }

    override suspend fun getNoteItemById(id: Int): NoteItem {
        return dao.getNoteById(id)
    }
}