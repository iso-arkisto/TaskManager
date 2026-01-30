package com.yourname.taskmanager.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.yourname.taskmanager.about_screen.AboutScreen
import com.yourname.taskmanager.add_item_screen.AddItemScreen
import com.yourname.taskmanager.note_list_screen.NoteListScreen
import com.yourname.taskmanager.settings_screen.SettingsScreen
import com.yourname.taskmanager.shopping_list_screen.ShoppingListScreen
import com.yourname.taskmanager.utils.Routes

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.SHOPPING_LIST
    ) {
        composable(Routes.SHOPPING_LIST) {
            ShoppingListScreen()
        }
        composable(Routes.ABOUT) {
            AboutScreen()
        }
        composable(Routes.SETTINGS) {
            SettingsScreen()
        }
        composable(Routes.ADD_ITEM) {
            AddItemScreen()
        }
        composable(Routes.NOTES_LIST) {
            NoteListScreen()
        }
    }
}