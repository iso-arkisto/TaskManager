package com.yourname.taskmanager.data.repository

import com.yourname.taskmanager.data.entity.NoteItem
import kotlinx.coroutines.flow.Flow

interface NoteItemRepository {
    suspend fun insertItem(item: NoteItem)
    suspend fun deleteItem(item: NoteItem)
    fun getAllItems(): Flow<List<NoteItem>>
    suspend fun getNoteItemById(id: Int): NoteItem
}