package com.yourname.taskmanager.screen.main_screen

import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun BottomNav(
    currentRoute: String?,
    onNavigate: (String) -> Unit
) {
    val listItems = listOf(
        BottomNavItem.ListItem,
        BottomNavItem.NoteItem,
        BottomNavItem.AboutItem,
        BottomNavItem.SettingsItem
    )

    BottomNavigation(
        modifier = Modifier.height(70.dp)
    ) {
        listItems.forEach {
            item ->
            BottomNavigationItem(
                selected = currentRoute == item.route,
                icon = {
                    Icon(
                       painter = painterResource(
                           id = item.icon
                       ),
                        contentDescription = item.title
                    )
                },
                onClick = { onNavigate(item.route) },
                label = {
                    Text(item.title)
                },
                alwaysShowLabel = false
            )
        }
    }
}