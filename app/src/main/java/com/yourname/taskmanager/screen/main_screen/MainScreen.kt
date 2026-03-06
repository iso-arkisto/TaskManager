package com.yourname.taskmanager.screen.main_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.room.util.TableInfo
import com.yourname.taskmanager.R
import com.yourname.taskmanager.dialog.MainDialog
import com.yourname.taskmanager.navigation.NavGraph
import com.yourname.taskmanager.utils.UIEvent

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel(),
    mainNavHostController: NavHostController
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    LaunchedEffect(currentRoute) {
        currentRoute?.let { route ->
            viewModel.updateFloatingButtonVisibility(route)
        }
    }

    LaunchedEffect(true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UIEvent.NavigateMain -> {
                    mainNavHostController.navigate(event.route)
                }

                is UIEvent.Navigate -> {
                    mainNavHostController.navigate(event.route)
                }

                else -> {}
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            bottomBar = {
                BottomNav(currentRoute) {
                    route ->
                    navController.navigate(route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                NavGraph(navController) {
                    route ->
                    viewModel.onEvent(MainScreenEvent.NavigateMain(route))
                }
                MainDialog(dialogController = viewModel)
            }
        }

        if(viewModel.showFloatingButton.value) {
            FloatingActionButton(
                onClick = {
                 viewModel.onEvent(MainScreenEvent.OnShowEditDialog)
                },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .offset(y = (-40).dp)
                    .size(56.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_add_24),
                    contentDescription = "Add",
                    tint = Color.White
                )
            }
        }
    }
}