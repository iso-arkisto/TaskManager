package com.yourname.taskmanager.screen.shopping_list_screen

import com.yourname.taskmanager.data.entity.ShoppingListItem

sealed class ShoppingListEvent {
    data class OnShowDeleteDialog(
        val item: ShoppingListItem
    ): ShoppingListEvent()

    data class OnShowEditDialog(
        val item: ShoppingListItem
    ): ShoppingListEvent()

    data class OnItemClick(
        val route: String
    ): ShoppingListEvent()

    object OnItemSave: ShoppingListEvent()
}