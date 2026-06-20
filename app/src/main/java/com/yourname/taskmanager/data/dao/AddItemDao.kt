package com.yourname.taskmanager.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.yourname.taskmanager.data.entity.AddItem
import com.yourname.taskmanager.data.entity.ShoppingListItem
import kotlinx.coroutines.flow.Flow

@Dao
interface AddItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: AddItem)

    @Delete
    suspend fun deleteItem(item: AddItem)

    @Update
    suspend fun updateItem(item: AddItem)

    @Query("SELECT * FROM add_item WHERE listId = :listId")
    fun getAllItemsById(listId: Int): Flow<List<AddItem>>

    @Query("SELECT * FROM add_item WHERE id = :id")
    suspend fun getItemById(id: Int): AddItem

    @Query("SELECT * FROM shopping_list_name_table WHERE id = :id")
    suspend fun getListItemById(id: Int): ShoppingListItem

    @Query("UPDATE shopping_list_name_table SET allItemsCount = allItemsCount+1, " +
            "allSelectedItemsCount = allSelectedItemsCount + :increment WHERE id = :listId")
    suspend fun updateShoppingListCount(listId: Int, increment: Int)
}