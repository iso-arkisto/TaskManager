package com.yourname.taskmanager.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("add_item")
data class AddItem(
    val listId: Int,
    @PrimaryKey val id: Int? = null,
    val name: String,
    val isChecked: Boolean
)