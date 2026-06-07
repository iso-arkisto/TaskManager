package com.yourname.taskmanager.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("note_table")
data class NoteItem(
    @PrimaryKey val id: Int? = null,
    val title: String,
    val description: String,
    val createdAt: Long,
    val updatedAt: Long = System.currentTimeMillis()
)