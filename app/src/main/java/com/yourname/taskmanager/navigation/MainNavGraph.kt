package com.yourname.taskmanager.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yourname.taskmanager.screen.add_item_screen.AddItemScreen
import com.yourname.taskmanager.screen.main_screen.MainScreen
import com.yourname.taskmanager.screen.new_note_screen.NewNoteScreen
import com.yourname.taskmanager.utils.Routes

@Composable
fun MainNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.MAIN_SCREEN
    ) {
        composable(Routes.ADD_ITEM + "/{listId}") {
            AddItemScreen()
        }
        composable(Routes.NEW_NOTE) {
            NewNoteScreen()
        }
        composable(Routes.MAIN_SCREEN) {
            MainScreen(mainNavHostController = navController)
        }
    }
}