package com.yourname.taskmanager.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface AddItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: AddItem)

    @Delete
    suspend fun deleteItem(item: AddItem)

    @Update
    suspend fun updateItem(item: AddItem)

    @Query("SELECT * FROM add_item WHERE listId = :listId")
    suspend fun getAllItemsById(listId: Int): List<AddItem>

    @Query("SELECT * FROM add_item WHERE id = :id")
    suspend fun getItemById(id: Int): AddItem

    @Query("SELECT * FROM shopping_list_name_table WHERE id = :id")
    suspend fun getListItemById(id: Int): ShoppingListItem

    @Query("UPDATE shopping_list_name_table SET allItemsCount = allItemsCount+1, " +
            "allSelectedItemsCount = allSelectedItemsCount + :increment WHERE id = :listId")
    suspend fun updateShoppingListCount(listId: Int, increment: Int)
}