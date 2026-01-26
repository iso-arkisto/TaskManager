package com.yourname.taskmanager.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("note_table")
data class NoteItem(
    @PrimaryKey val id: Int? = null,
    val title: String,
    val description: String,
    val time: String,
)