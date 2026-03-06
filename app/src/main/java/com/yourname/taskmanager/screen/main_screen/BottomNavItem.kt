package com.yourname.taskmanager.screen.main_screen

import com.yourname.taskmanager.R
import com.yourname.taskmanager.utils.Routes


sealed class BottomNavItem(
    val title: String,
    val icon: Int,
    val route: String
) {
    object ListItem: BottomNavItem (
        title = "Lists",
        icon = R.drawable.list,
        route = Routes.SHOPPING_LIST
    )

    object NoteItem: BottomNavItem (
        title = "Notes",
        icon = R.drawable.note,
        route = Routes.NOTES_LIST
    )

    object AboutItem: BottomNavItem (
        title = "About",
        icon = R.drawable.about,
        route = Routes.ABOUT
    )

    object SettingsItem: BottomNavItem (
        title = "Settings",
        icon = R.drawable.settings,
        route = Routes.SETTINGS
    )
}