package com.yourname.taskmanager.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("shopping_list_name_table")
data class ShoppingListItem(
    @PrimaryKey val id: Int? = null,
    val name: String,
    val title: String,
    val allItemsCount: Int,
    val allSelectedItemsCount: Int,
)
