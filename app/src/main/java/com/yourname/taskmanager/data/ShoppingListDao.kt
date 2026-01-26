package com.yourname.taskmanager.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: ShoppingListItem)

    @Delete
    suspend fun deleteItem(item: ShoppingListItem)

    @Query("DELETE FROM add_item WHERE listId = :listId")
    suspend fun deleteAddItems(listId: Int)

    @Transaction
    suspend fun deleteShoppingList(item: ShoppingListItem) {
        deleteAddItems(item.id!!)
        deleteItem(item)
    }

    @Update
    suspend fun updateItem(item: ShoppingListItem)

    @Query("SELECT * FROM shopping_list_name_table")
    fun getAllItems(): Flow<List<ShoppingListItem>>

    @Query("SELECT * FROM shopping_list_name_table WHERE id = :id")
    suspend fun getItemById(id: Int): ShoppingListItem?

    @Query("UPDATE shopping_list_name_table SET allItemsCount = allItemsCount+1, " +
            "allSelectedItemsCount = allSelectedItemsCount + :increment WHERE id = :listId")
    suspend fun updateShoppingListCount(listId: Int, increment: Int)
}