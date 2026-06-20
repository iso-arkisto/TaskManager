package com.yourname.taskmanager.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yourname.taskmanager.data.dao.AddItemDao
import com.yourname.taskmanager.data.dao.NoteItemDao
import com.yourname.taskmanager.data.dao.ShoppingListDao
import com.yourname.taskmanager.data.entity.AddItem
import com.yourname.taskmanager.data.entity.NoteItem
import com.yourname.taskmanager.data.entity.ShoppingListItem

@Database(entities =
    [ShoppingListItem::class,
        AddItem::class,
        NoteItem::class
    ],
    version = 3,
    exportSchema = true
    )
abstract class MainDb: RoomDatabase() {
    abstract val shoppingListDao: ShoppingListDao
    abstract val addItemDao: AddItemDao
    abstract val noteItemDao: NoteItemDao
}
