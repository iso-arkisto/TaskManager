package com.yourname.taskmanager.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities =
    [ShoppingListItem::class,
        AddItem::class,
        NoteItem::class
    ],
    version = 1,
    exportSchema = true
    )
abstract class MainDb: RoomDatabase() {
    abstract val shoppingListDao: ShoppingListDao
    abstract val addItemDao: AddItemDao
    abstract val noteItemDao: NoteItemDao
}
